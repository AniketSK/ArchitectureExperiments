package com.aniketkadam.temperatureapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderForTest::class,
        NetworkModuleForTest::class
    ]
)
interface AppComponentForTest : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)
    fun provideOkHttp(): OkHttpClient

    @Component.Builder
    interface Builder {


        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponentForTest
    }
}