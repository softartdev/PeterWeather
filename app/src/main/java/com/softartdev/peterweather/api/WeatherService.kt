package com.softartdev.peterweather.api

import com.softartdev.peterweather.entity.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("id") id: Int,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<Weather>
}