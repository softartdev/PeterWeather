package com.softartdev.peterweather.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softartdev.peterweather.R
import com.softartdev.peterweather.model.ResourceState
import com.softartdev.peterweather.model.WeatherState
import com.softartdev.peterweather.ui.common.gone
import com.softartdev.peterweather.ui.common.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error.view.*

class MainActivity : AppCompatActivity(), Observer<WeatherState>, View.OnClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.weatherLiveData.observe(this, this)

        main_update_button.setOnClickListener(this)
        main_error_view.error_try_again_button.setOnClickListener(this)
    }

    override fun onChanged(weatherState: WeatherState) = when (weatherState.resourceState) {
        ResourceState.LOADING -> {
            main_content_view.gone()
            main_error_view.gone()
            main_progress_view.visible()
        }
        ResourceState.SUCCESS -> {
            val temp: String? = weatherState.weather?.main?.temp?.toString()
            main_temp_text_view.text = getString(R.string.temperature, temp)
            val speed: String? = weatherState.weather?.wind?.speed?.toString()
            main_wind_speed_text_view.text = getString(R.string.wind_speed, speed)
            val cloudiness: String? = weatherState.weather?.clouds?.all?.toString()
            main_clouds_text_view.text = getString(R.string.cloudiness, cloudiness)
            main_progress_view.gone()
            main_error_view.gone()
            main_content_view.visible()
        }
        ResourceState.ERROR -> {
            val message = weatherState.errorMessage ?: getString(R.string.label_error_result)
            main_error_view.error_message_text_view.text = message
            main_content_view.gone()
            main_progress_view.gone()
            main_error_view.visible()
        }
    }

    override fun onClick(v: View?) = viewModel.loadWeather()
}
