package com.aniketkadam.temperatureapp.temperaturedisplay.data

class FormattedForecastForCity(val apiResponse: ForecastApiResponse) {

    val city = apiResponse.currentCity

    val temp by lazy {
        TempratureFormatter().formattedTemperature(apiResponse.currentTemp)
    }

}