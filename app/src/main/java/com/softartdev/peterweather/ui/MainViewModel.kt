package com.softartdev.peterweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softartdev.peterweather.api.WeatherFactory
import com.softartdev.peterweather.api.WeatherRepo
import com.softartdev.peterweather.entity.Weather
import com.softartdev.peterweather.model.WeatherState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val weatherRepo: WeatherRepo by lazy { WeatherFactory.makeWeatherRepo() }

    val weatherLiveData: MutableLiveData<WeatherState> = MutableLiveData()

    init {
        loadWeather()
    }

    fun loadWeather() {
        weatherLiveData.postValue(WeatherState.Loading)
        weatherRepo.weather().enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weather = response.body()
                weatherLiveData.postValue(WeatherState.Success(weather))
            }
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                weatherLiveData.postValue(WeatherState.Error(t.message))
            }
        })
    }
}
