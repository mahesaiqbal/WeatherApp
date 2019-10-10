package com.mahesaiqbal.weatherapp.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.weatherapp.data.source.local.LocalRepository
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.data.source.remote.ApiResponse
import com.mahesaiqbal.weatherapp.data.source.remote.RemoteRepository
import com.mahesaiqbal.weatherapp.data.source.remote.response.DailyForecastResponse
import com.mahesaiqbal.weatherapp.data.source.remote.response.X
import com.mahesaiqbal.weatherapp.utils.AppExecutors
import com.mahesaiqbal.weatherapp.utils.dateFormat
import com.mahesaiqbal.weatherapp.vo.Resource

class WeatherRepository(
    var localRepository: LocalRepository,
    var remoteRepository: RemoteRepository,
    var appExecutors: AppExecutors
) : WeatherDataSource {

    companion object {
        @Volatile
        private var INSTANCE: WeatherRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteData: RemoteRepository,
            appExecutor: AppExecutors
        ): WeatherRepository? {
            if (INSTANCE == null) {
                synchronized(WeatherRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = WeatherRepository(localRepository, remoteData, appExecutor)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getDailyForecast(): LiveData<Resource<List<WeatherEntity>>> {
        return object : NetworkBoundResource<List<WeatherEntity>, DailyForecastResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<WeatherEntity>> {
                return localRepository.getDailyForecast()
            }

            override fun shouldFetch(data: List<WeatherEntity>): Boolean {
                return data == null || data.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<DailyForecastResponse>>? {
                return remoteRepository.getDailyForecast()
            }

            override fun saveCallResult(data: DailyForecastResponse?) {
                val dailyForecast = arrayListOf<WeatherEntity>()

                for (i in data!!.list.indices) {

                    val response: X = data.list[i]
                    val forecast = WeatherEntity(
                        0,
                        data.city.name,
                        response.dt,
                        response.temp.day,
                        response.weather[0].main,
                        response.weather[0].icon
                    )

                    dailyForecast.add(forecast)
                }

                localRepository.insertDailyForecast(dailyForecast)
            }

        }.asLiveData()
    }
}
