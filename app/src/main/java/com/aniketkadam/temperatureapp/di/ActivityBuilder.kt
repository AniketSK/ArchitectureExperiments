package com.aniketkadam.temperatureapp.di

import com.aniketkadam.temperatureapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBuilder::class])
    abstract fun provideMainActivity(): MainActivity
}