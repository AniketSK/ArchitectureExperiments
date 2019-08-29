package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.temperatureapp.temperaturedisplay.data.ForecastApiResponse
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class TemperatureVmTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    val repository = mockk<TemperatureDisplayRepository> {
        every { getCurrentWeather() } returns Observable.just(
            ForecastApiResponse(
                "Mumbai",
                30f,
                null
            )
        )
    }

    @Test
    fun `when the vm is loaded, it retrieves the current weather`() {

        val vm = TemperatureVm(repository)

        verify(exactly = 1) { repository.getCurrentWeather() }

        assertThat(
            vm.currentWeather.value,
            equalTo<LceWeather>(
                LceWeather.Success(
                    ForecastApiResponse(
                        "Mumbai",
                        30f,
                        null
                    )
                )
            )
        )
    }


    @Test
    fun `when the vm is created loading is shown first`() {
        val repository = mockk<TemperatureDisplayRepository> {
            every { getCurrentWeather() } returns Observable.error(SocketTimeoutException("read timed out"))
        }
        val vm = TemperatureVm(repository)

        assertThat(vm.currentWeather.value, instanceOf(LceWeather.Error::class.java))
    }

    @Test
    fun `loading is shown until an item is emitted from the repository`() {
        val repository = mockk<TemperatureDisplayRepository> {
            every { getCurrentWeather() } returns Observable.empty()
        }
        val vm = TemperatureVm(repository)
        vm.currentWeather.test().awaitValue().assertValue(LceWeather.Loading)
    }

    @Test
    fun `when the weather is retried, it loads again`() {
        val vm = TemperatureVm(repository)
        vm.retry()
        verify(exactly = 2) { repository.getCurrentWeather() }
    }

    @Test
    fun `if a load request is active, do not try to initiate another one`() {
        val scheduler = TestScheduler()
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        val vm = TemperatureVm(repository)
        val loadStates = vm.currentWeather.test()
        loadStates.assertHistorySize(1).assertValue(LceWeather.Loading)
        vm.retry()
        loadStates.assertHistorySize(1).assertValue(LceWeather.Loading)
        scheduler.advanceTimeBy(5, TimeUnit.SECONDS)
        loadStates.assertHistorySize(2).assertValue { it is LceWeather.Success }
        verify(exactly = 1) { repository.getCurrentWeather() }
        RxJavaPlugins.reset()
    }
}