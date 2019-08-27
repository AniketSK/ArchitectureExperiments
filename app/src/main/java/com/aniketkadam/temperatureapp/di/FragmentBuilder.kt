package com.aniketkadam.temperatureapp.di

import com.aniketkadam.temperatureapp.TemperatureDisplayFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun buildTemperatureDisplayFragment(): TemperatureDisplayFragment
}