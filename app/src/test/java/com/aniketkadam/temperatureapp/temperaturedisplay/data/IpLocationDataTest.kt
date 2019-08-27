package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.aniketkadam.temperatureapp.getTextInFile
import com.google.gson.Gson
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class IpLocationDataTest {

    private lateinit var gson: Gson
    private val expected = IpLocationData("Mumbai")

    @Before
    fun setUp() {
        gson = Gson()
    }

    @Test
    fun `ip location serializes correctly`() {
        val result = gson.fromJson(
            getTextInFile("api_response_for_ip_location.json"),
            IpLocationData::class.java
        )
        assertThat(expected, equalTo(result))
    }
}