package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @field:SerializedName("date") val date: String,
    @field:SerializedName("day") val temperature: Temperature
)