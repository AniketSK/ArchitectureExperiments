package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.aniketkadam.temperatureapp.temperaturedisplay.network.ForecastDayDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(ForecastDayDeserializer::class)
data class ForecastDay(
    val date: String,
    val temperature: Float
)