package com.aniketkadam.temperatureapp.di

import androidx.lifecycle.ViewModelProviders
import com.aniketkadam.temperatureapp.MainActivity
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureDisplayRepository
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureFragmentFactory
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

const val MAIN_VM = "MAIN_VM"
const val FRAGMENT_VM = "FRAGMENT_VM"

@Module
object MainActivityModule {

    @JvmStatic
    @Named(MAIN_VM)
    @Provides
    fun getTemperatureVm(mainActivity: MainActivity, factory: TemperatureFragmentFactory) =
        ViewModelProviders.of(mainActivity, factory).get(TemperatureVm::class.java)

    @JvmStatic
    @Named(FRAGMENT_VM)
    @Provides
    fun getTemperatureVmForFragments(mainActivity: MainActivity) =
        ViewModelProviders.of(mainActivity).get(TemperatureVm::class.java)

    @JvmStatic
    @Provides
    fun getTemperatureVmFactory(repository: TemperatureDisplayRepository) =
        TemperatureFragmentFactory(repository)

    @JvmStatic
    @Provides
    fun getWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}