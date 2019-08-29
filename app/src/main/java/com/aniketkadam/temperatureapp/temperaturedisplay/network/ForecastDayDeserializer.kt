package com.aniketkadam.temperatureapp.temperaturedisplay.network

import com.aniketkadam.temperatureapp.temperaturedisplay.data.ForecastDay
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ForecastDayDeserializer : JsonDeserializer<ForecastDay> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ForecastDay? {

        val forecastDayJson = json?.asJsonObject ?: return null

        val dateString = forecastDayJson.get("date").asString
        val averageTemp = forecastDayJson.get("day").asJsonObject.get("avgtemp_c").asFloat

        return ForecastDay(
            dateString,
            averageTemp
        )

    }

}