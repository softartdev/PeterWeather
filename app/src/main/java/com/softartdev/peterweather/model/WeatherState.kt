package com.softartdev.peterweather.model

import com.softartdev.peterweather.entity.Weather

sealed class WeatherState(
    val resourceState: ResourceState,
    val weather: Weather? = null,
    val errorMessage: String? = null
): Resource<Weather>(resourceState, weather, errorMessage) {

    data class Success(private val result: Weather?) : WeatherState(ResourceState.SUCCESS, result)

    data class Error(private val error: String? = null) : WeatherState(ResourceState.ERROR, errorMessage = error)

    object Loading : WeatherState(ResourceState.LOADING)
}