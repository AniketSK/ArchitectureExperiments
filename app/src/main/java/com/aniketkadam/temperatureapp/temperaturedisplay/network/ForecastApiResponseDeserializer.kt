package com.aniketkadam.temperatureapp.temperaturedisplay.network

import com.aniketkadam.temperatureapp.temperaturedisplay.data.ForecastApiResponse
import com.aniketkadam.temperatureapp.temperaturedisplay.data.ForecastDay
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ForecastApiResponseDeserializer : JsonDeserializer<ForecastApiResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ForecastApiResponse? {

        val jsonForecastApi = json?.asJsonObject ?: return null
        val currentCity = jsonForecastApi.get("location").asJsonObject.get("name").asString
        val currentTemp = jsonForecastApi.get("current").asJsonObject.get("temp_c").asFloat

        val forecastDays = context?.deserialize<List<ForecastDay>>(
            jsonForecastApi.get("forecast").asJsonObject.get("forecastday"),
            object : TypeToken<List<ForecastDay>>() {}.type
        )

        return ForecastApiResponse(
            currentCity,
            currentTemp,
            forecastDays
        )
    }

}