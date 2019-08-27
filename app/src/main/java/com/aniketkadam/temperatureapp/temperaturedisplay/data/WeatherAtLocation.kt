package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class WeatherAtLocation(
    @SerializedName("location") val location: WeatherLocation,
    @SerializedName("current") val temperature: Temperature
)