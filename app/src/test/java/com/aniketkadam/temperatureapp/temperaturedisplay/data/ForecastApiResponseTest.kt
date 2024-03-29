package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.aniketkadam.temperatureapp.getTextInFile
import com.google.gson.Gson
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test

class ForecastApiResponseTest {

    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = Gson()
    }

    @Test
    fun `forecast api reponse serializes correctly`() {

        val expectedResponse = ForecastApiResponse(
            "Mumbai", 29f,
            listOf(
                ForecastDay("2019-08-28", 28.3f),
                ForecastDay("2019-08-29", 28.2f),
                ForecastDay("2019-08-30", 28.1f),
                ForecastDay("2019-08-31", 28f)
            )
        )


        val serializedReponse = gson.fromJson(
            getTextInFile("api_response_for_4_day_forecast.json"),
            ForecastApiResponse::class.java
        )
        assertThat(serializedReponse, equalTo(expectedResponse))
    }

}