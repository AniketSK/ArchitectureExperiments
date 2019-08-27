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
import org.junit.Rule
import org.junit.Test

class TemperatureFragmentVmTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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

}