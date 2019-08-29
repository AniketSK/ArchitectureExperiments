package com.aniketkadam.temperatureapp.temperaturedisplay.data

import com.aniketkadam.temperatureapp.BuildConfig
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("ip.json?q=auto:ip")
    fun getIpLocation(@Query("key") key: String = BuildConfig.WEATHER_API_KEY): Observable<IpLocationData>

    @GET("current.json")
    fun getCurrentWeatherAtLocation(
        @Query("q") city: String,
        @Query("key") key: String = BuildConfig.WEATHER_API_KEY
    ): Observable<WeatherAtLocation>

    @GET("forecast.json")
    fun getForecastForDays(
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("key") key: String = BuildConfig.WEATHER_API_KEY
    ): Observable<ForecastApiResponse>
}