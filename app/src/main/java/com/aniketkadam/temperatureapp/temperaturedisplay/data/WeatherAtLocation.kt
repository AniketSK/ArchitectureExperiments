package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class WeatherAtLocation(
    @field:SerializedName("location") val location: WeatherLocation,
    @field:SerializedName("current") val temperature: Temperature
)