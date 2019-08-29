package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class ForecastData(
    @field:SerializedName("forecastday") val days: List<ForecastDay>
)
