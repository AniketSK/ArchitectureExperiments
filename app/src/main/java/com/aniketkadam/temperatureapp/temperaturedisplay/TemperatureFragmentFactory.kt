package com.aniketkadam.temperatureapp.temperaturedisplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TemperatureFragmentFactory(private val repository: TemperatureDisplayRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(TemperatureVm::class.java) -> TemperatureVm(repository)
            else -> throw IllegalArgumentException("Unknown model class ${modelClass.name}")
        }
    } as T
}