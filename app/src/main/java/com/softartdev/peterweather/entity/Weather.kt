package com.softartdev.peterweather.entity

data class Weather(
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val name: String
)
