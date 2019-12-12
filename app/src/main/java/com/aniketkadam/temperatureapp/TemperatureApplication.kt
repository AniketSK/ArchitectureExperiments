package com.aniketkadam.temperatureapp

import com.aniketkadam.temperatureapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TemperatureApplication : DaggerApplication() {

    private val appInjector by lazy {
        DaggerAppComponent.builder().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appInjector

}