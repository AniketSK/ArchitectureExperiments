package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.aniketkadam.temperatureapp.getTextInFile
import com.google.gson.Gson
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class WeatherAtLocationTest {

    lateinit var gson: Gson
    private val expectedWeatherAtLocation = WeatherAtLocation(
        WeatherLocation("Mumbai"),
        Temperature(30f)
    )

    @Before
    fun setup() {
        gson = Gson()
    }

    @Test
    fun `api response serializes as expected`() {
        val weatherAtLocation: WeatherAtLocation = gson.fromJson(
            getTextInFile("api_response_for_current.json"),
            WeatherAtLocation::class.java
        )
        assertThat(weatherAtLocation, equalTo(expectedWeatherAtLocation))
    }
}