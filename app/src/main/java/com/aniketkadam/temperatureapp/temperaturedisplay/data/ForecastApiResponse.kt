package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.aniketkadam.temperatureapp.temperaturedisplay.network.ForecastApiResponseDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(ForecastApiResponseDeserializer::class)
data class ForecastApiResponse(
    val currentCity: String,
    val currentTemp: Float,
    val forecastDay: List<ForecastDay>?
)