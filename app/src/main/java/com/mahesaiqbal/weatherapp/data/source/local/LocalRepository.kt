package com.mahesaiqbal.weatherapp.data.source.local

import androidx.lifecycle.LiveData
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.data.source.local.room.WeatherDao

class LocalRepository(val mWeatherDao: WeatherDao) {

    companion object {
        private var INSTANCE: LocalRepository? = null

        fun getInstance(weatherDao: WeatherDao): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(weatherDao)
            }
            return INSTANCE!!
        }
    }

    fun getDailyForecast(): LiveData<List<WeatherEntity>> = mWeatherDao.getWeatherForecast()

    fun insertDailyForecast(weatherForecast: List<WeatherEntity>) {
        mWeatherDao.insertWeatherForecast(weatherForecast)
    }
}
