package com.mahesaiqbal.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.mahesaiqbal.weatherapp.data.source.WeatherRepository
import com.mahesaiqbal.weatherapp.di.Injection
import com.mahesaiqbal.weatherapp.ui.home.MainViewModel

class ViewModelFactory(weatherRepository: WeatherRepository) : NewInstanceFactory() {

    var mWeatherRepository: WeatherRepository = weatherRepository

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mWeatherRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
