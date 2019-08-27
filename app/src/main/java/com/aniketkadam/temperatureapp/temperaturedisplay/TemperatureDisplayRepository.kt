package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.annotation.WorkerThread
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherApi
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation
import io.reactivex.Observable

class TemperatureDisplayRepository(private val weatherApi: WeatherApi) {

    @WorkerThread
    fun getCurrentWeather(): Observable<WeatherAtLocation> =
        weatherApi.getIpLocation().switchMap { weatherApi.getCurrentWeatherAtLocation(it.city) }

    
}
