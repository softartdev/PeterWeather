package com.softartdev.peterweather.api

import com.softartdev.peterweather.entity.Weather
import retrofit2.Call

class WeatherRepo(
    private val weatherService: WeatherService
) {

    fun weather(): Call<Weather> = weatherService.getWeather(
        id = CITY_ID,
        units = UNITS,
        appid = APP_ID
    )

    companion object {
        private const val CITY_ID = 498817
        private const val UNITS = "metric"
        private const val APP_ID = "b6907d289e10d714a6e88b30761fae22"
    }
}