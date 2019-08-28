package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.temperatureapp.temperaturedisplay.data.Temperature
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherLocation
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException

class TemperatureVmTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    val repository = mockk<TemperatureDisplayRepository> {
        every { getCurrentWeather() } returns Observable.just(
            WeatherAtLocation(
                WeatherLocation("Mumbai"),
                Temperature(30f)
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
                    WeatherAtLocation(
                        WeatherLocation("Mumbai"),
                        Temperature(30f)
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
}