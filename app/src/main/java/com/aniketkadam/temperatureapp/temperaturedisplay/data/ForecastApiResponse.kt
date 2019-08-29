package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class ForecastApiResponse(
    @field:SerializedName("location") val location: WeatherLocation,
    @field:SerializedName("current") val temperature: Temperature,
    @field:SerializedName("forecast") val forecastData: ForecastData
)