package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.temperatureapp.temperaturedisplay.data.ForecastApiResponse
import com.aniketkadam.temperatureapp.temperaturedisplay.data.FormattedForecastDay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TemperatureVm @Inject constructor(private val repository: TemperatureDisplayRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val _currentWeather = MutableLiveData<LceWeather>()

    init {
        loadWeather()
    }

    private fun loadWeather() {
        if (_currentWeather.value != LceWeather.Loading) {
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
    }

    val currentWeather: LiveData<LceWeather>
        get() = _currentWeather

    val currentWeatherState = Transformations.map(_currentWeather) {
        if (it is LceWeather.Success) {
            return@map CurrentWeatherState(
                it.forecast.currentTemp,
                it.forecast.currentCity,
                it.forecast.forecastDay?.map { FormattedForecastDay(it) }
            )
        } else null
    }

    fun retry() = loadWeather()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

sealed class LceWeather {
    data class Success(val forecast: ForecastApiResponse) : LceWeather()
    data class Error(val e: Throwable) : LceWeather()
    object Loading : LceWeather()
}

data class CurrentWeatherState(
    val temp: Float,
    val city: String,
    val daysForecast: List<FormattedForecastDay>?
)
