package com.mahesaiqbal.weatherapp.di

import android.app.Application
import com.mahesaiqbal.weatherapp.data.source.WeatherRepository
import com.mahesaiqbal.weatherapp.data.source.local.LocalRepository
import com.mahesaiqbal.weatherapp.data.source.local.room.WeatherDatabase
import com.mahesaiqbal.weatherapp.data.source.remote.RemoteRepository
import com.mahesaiqbal.weatherapp.utils.AppExecutors

class Injection {

    companion object {
        fun provideRepository(application: Application): WeatherRepository {
            val database = WeatherDatabase.getInstance(application)

            val localRepository = LocalRepository.getInstance(database.weatherDao())
            val remoteRepository = RemoteRepository.getInstance()

            val appExecutors = AppExecutors()

            return WeatherRepository.getInstance(localRepository, remoteRepository!!, appExecutors)!!
        }
    }
}
