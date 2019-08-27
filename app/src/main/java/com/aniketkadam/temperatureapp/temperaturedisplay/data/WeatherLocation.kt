package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class WeatherLocation(
    @field:SerializedName("name") val name: String
)