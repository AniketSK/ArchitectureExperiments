package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherAtLocation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TemperatureFragmentVm(repository: TemperatureDisplayRepository) : ViewModel() {

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
