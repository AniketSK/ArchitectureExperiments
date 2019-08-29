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

    @Test
    fun `the temperature from forecast has a single decimal point if present`() {
        val sampleForecast = ForecastDay("2019-08-12", 21.2f)
        val underTest = FormattedForecastDay(sampleForecast)

        assertThat(underTest.temp, equalTo("21.2"))
    }

    @Test
    fun `the temperature from forecast has no decimal points at all if it ends with zero`() {
        val sampleForecast = ForecastDay("2019-08-12", 21.0f)
        val underTest = FormattedForecastDay(sampleForecast)

        assertThat(underTest.temp, equalTo("21"))

    }
}