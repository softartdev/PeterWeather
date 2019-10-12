package com.softartdev.peterweather.api

import com.softartdev.peterweather.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherFactory {

    fun makeWeatherRepo(): WeatherRepo = WeatherRepo(
        weatherService = makeWeatherService()
    )

    private fun makeWeatherService(): WeatherService = Retrofit.Builder()
        .baseUrl("https://openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create())
        .client(makeOkHttpClient())
        .build()
        .create(WeatherService::class.java)

    private fun makeOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .build()

    private fun makeLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) BODY else NONE
    }
}