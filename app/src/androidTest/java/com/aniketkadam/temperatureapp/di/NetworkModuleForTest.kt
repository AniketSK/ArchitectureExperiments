package com.aniketkadam.temperatureapp.di

import dagger.Module
import dagger.Provides
import io.appflate.restmock.RESTMockServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModuleForTest {
    @Singleton
    @Provides
    fun getRetrofit(client: OkHttpClient) =
        Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
            GsonConverterFactory.create()
        )
            .baseUrl(RESTMockServer.getUrl())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .sslSocketFactory(
                RESTMockServer.getSSLSocketFactory(),
                RESTMockServer.getTrustManager()
            )
            .build()

}