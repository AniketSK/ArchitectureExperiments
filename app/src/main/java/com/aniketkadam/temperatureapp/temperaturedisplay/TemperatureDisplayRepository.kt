package com.aniketkadam.temperatureapp.temperaturedisplay

import com.aniketkadam.temperatureapp.temperaturedisplay.data.ForecastApiResponse
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherApi
import io.reactivex.Observable
import javax.inject.Inject

class TemperatureDisplayRepository @Inject constructor(private val weatherApi: WeatherApi) {

    fun getCurrentWeather(): Observable<ForecastApiResponse> =
        weatherApi.getIpLocation().switchMap { weatherApi.getForecastForDays(it.city, 4) }

}
