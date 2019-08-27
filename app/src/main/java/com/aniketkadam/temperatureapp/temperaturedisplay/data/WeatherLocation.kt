package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class WeatherLocation(
    @SerializedName("name") val name: String
)