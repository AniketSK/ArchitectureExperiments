package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation

class TemperatureFragmentVm(private val repository: TemperatureDisplayRepository) : ViewModel() {

    private val _currentWeather = MutableLiveData<LceWeather>()

    val currentWeather: LiveData<LceWeather>
        get() = _currentWeather

}

sealed class LceWeather {
    data class Success(val weatherAtLocation: WeatherAtLocation) : LceWeather()
    data class Error(val e: Throwable) : LceWeather()
    object Loading : LceWeather()
}
