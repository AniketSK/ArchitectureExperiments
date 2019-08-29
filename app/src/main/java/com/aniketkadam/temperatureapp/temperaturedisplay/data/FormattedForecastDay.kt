package com.aniketkadam.temperatureapp.temperaturedisplay.data

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat") // The date conversion is unaffected by locale, a date is always the same day no matter which locale you look in.
data class FormattedForecastDay(private val forecastDay: ForecastDay) {

    val date: String by lazy {
        val givenDate = SimpleDateFormat("yyyy-MM-dd").parse(forecastDay.date)
        val formattedDay = SimpleDateFormat("EEEE").format(givenDate)
        formattedDay
    }

    val temp
        get() = DecimalFormat("0.#").format(forecastDay.temperature)
}
