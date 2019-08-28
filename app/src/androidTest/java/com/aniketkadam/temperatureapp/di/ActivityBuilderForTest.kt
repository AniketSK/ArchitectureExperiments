package com.aniketkadam.temperatureapp.di

import com.aniketkadam.temperatureapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderForTest {

    @ContributesAndroidInjector(modules = [MainActivityModuleForTest::class, FragmentBuilder::class])
    abstract fun provideMainActivity(): MainActivity
}