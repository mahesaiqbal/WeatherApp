package com.mahesaiqbal.weatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.weatherapp.data.source.WeatherRepository
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.data.source.remote.RemoteRepository
import com.mahesaiqbal.weatherapp.vo.Resource

class MainViewModel(var weatherRepository: WeatherRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    fun getDailyForecast(): LiveData<Resource<List<WeatherEntity>>> = weatherRepository.getDailyForecast()

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}
