package com.mahesaiqbal.weatherapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mahesaiqbal.weatherapp.R
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.viewmodel.ViewModelFactory
import com.mahesaiqbal.weatherapp.vo.Resource
import com.mahesaiqbal.weatherapp.vo.Status.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var weatherAdapter: MainAdapter
    lateinit var viewModel: MainViewModel

    var dailyForecast: List<WeatherEntity> = arrayListOf()

    companion object {
        fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_bar.visibility = View.VISIBLE

        viewModel = obtainViewModel(this)

        weatherAdapter = MainAdapter(this, dailyForecast)

        viewModel.getDailyForecast().observe(this, getDailyForecast)
    }

    private val getDailyForecast = Observer<Resource<List<WeatherEntity>>> { resultMovie ->
        if (resultMovie != null) {
            when (resultMovie.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    weatherAdapter = MainAdapter(this, resultMovie.data!!)

                    populateMainView(resultMovie.data)

                    rv_weather_forecast.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = weatherAdapter
                    }

                    weatherAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun populateListView(mainAdapter: MainAdapter) {
        rv_weather_forecast.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }

    private fun populateMainView(data: List<WeatherEntity>) {
        tv_area.text = data[0].cityName
        tv_datetime.text = data[0].datetime
        tv_celcius.text = "${data[0].tempDay}°"
        tv_weather_condition.text = data[0].weatherMain

        Glide.with(this)
            .load("http://openweathermap.org/img/wn/${data[0].weatherIcon}@2x.png")
            .into(img_weather_condition)
    }
}
