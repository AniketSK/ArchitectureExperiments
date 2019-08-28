package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TemperatureVm @Inject constructor(repository: TemperatureDisplayRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val _currentWeather = MutableLiveData<LceWeather>()

    init {
        disposable.add(
            repository.getCurrentWeather().subscribeOn(Schedulers.io())
                .map<LceWeather> { LceWeather.Success(it) }
                .onErrorReturn { LceWeather.Error(it) }
                .startWith(LceWeather.Loading)
                .subscribe {
                    _currentWeather.postValue(it)
                }
        )
    }

    val currentWeather: LiveData<LceWeather>
        get() = _currentWeather

    val currentWeatherState = Transformations.map(_currentWeather) {
        if (it is LceWeather.Success) {
            return@map CurrentWeatherState(
                it.weatherAtLocation.temperature.celsius,
                it.weatherAtLocation.location.name
            )
        } else null
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

sealed class LceWeather {
    data class Success(val weatherAtLocation: WeatherAtLocation) : LceWeather()
    data class Error(val e: Throwable) : LceWeather()
    object Loading : LceWeather()
}

data class CurrentWeatherState(
    val temp: Float,
    val city: String
)