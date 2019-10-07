package com.mahesaiqbal.weatherapp.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.vo.Resource

interface WeatherDataSource {
    fun getDailyForecast(): LiveData<Resource<List<WeatherEntity>>>
}
