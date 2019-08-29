package com.aniketkadam.temperatureapp.temperaturedisplay.data

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FormattedForecastDayTest {

    @Test
    fun `the day of week from forecast is correctly formatted`() {
        val sampleForecast = ForecastDay("2019-08-12", 21f)
        val underTest = FormattedForecastDay(sampleForecast)

        assertThat(underTest.date, equalTo("Monday"))
    }
}