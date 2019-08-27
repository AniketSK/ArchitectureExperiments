package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.temperatureapp.temperaturedisplay.data.Temperature
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherLocation
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

class TemperatureFragmentVmTest {

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

        val vm = TemperatureFragmentVm(repository)

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
        val vm = TemperatureFragmentVm(repository)

        assertThat(vm.currentWeather.value, instanceOf(LceWeather.Error::class.java))
    }
}