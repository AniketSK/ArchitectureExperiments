package com.aniketkadam.temperatureapp

import android.content.Context
import androidx.multidex.MultiDex
import com.aniketkadam.temperatureapp.di.DaggerAppComponentForTest
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class TestApplication : DaggerApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    val appInjector by lazy {
        DaggerAppComponentForTest.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appInjector

}