package com.aniketkadam.temperatureapp.temperaturedisplay

import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherApi
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation
import io.reactivex.Observable
import javax.inject.Inject

class TemperatureDisplayRepository @Inject constructor(private val weatherApi: WeatherApi) {

    fun getCurrentWeather(): Observable<WeatherAtLocation> =
        weatherApi.getIpLocation().switchMap { weatherApi.getCurrentWeatherAtLocation(it.city) }

}
