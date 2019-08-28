package com.aniketkadam.temperatureapp.di

import androidx.lifecycle.ViewModelProviders
import com.aniketkadam.temperatureapp.MainActivity
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureDisplayRepository
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureFragmentFactory
import com.aniketkadam.temperatureapp.temperaturedisplay.TemperatureVm
import com.aniketkadam.temperatureapp.temperaturedisplay.data.WeatherApi
import dagger.Module
import dagger.Provides
import io.appflate.restmock.RESTMockServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class MainActivityModuleForTest {

    @Named(MAIN_VM)
    @Provides
    fun getTemperatureVm(mainActivity: MainActivity, factory: TemperatureFragmentFactory) =
        ViewModelProviders.of(mainActivity, factory).get(TemperatureVm::class.java)

    @Named(FRAGMENT_VM)
    @Provides
    fun getTemperatureVmForFragments(mainActivity: MainActivity) =
        ViewModelProviders.of(mainActivity).get(TemperatureVm::class.java)


    @Provides
    fun getTemperatureVmFactory(repository: TemperatureDisplayRepository) =
        TemperatureFragmentFactory(repository)

    @Provides
    fun getWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    fun getRetrofit() =
        Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
            GsonConverterFactory.create()
        )
            .baseUrl(RESTMockServer.getUrl())
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .sslSocketFactory(
                        RESTMockServer.getSSLSocketFactory(),
                        RESTMockServer.getTrustManager()
                    )
                    .build()
            )
            .build()

}