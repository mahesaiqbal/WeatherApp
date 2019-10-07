package com.mahesaiqbal.weatherapp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahesaiqbal.weatherapp.data.source.remote.response.DailyForecastResponse
import com.mahesaiqbal.weatherapp.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

class RemoteRepository {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE
        }
    }

    private val apiService = Client.create()

    val API_KEY = "dd522c8235e5b956c6637e8a72a46189"

    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    fun getDailyForecast(): LiveData<ApiResponse<DailyForecastResponse>> {

        val resultPopularMovie: MutableLiveData<ApiResponse<DailyForecastResponse>> = MutableLiveData()

        GlobalScope.async(coroutineContext) {
            val response = apiService.getDailyForecast("Jakarta,ID", "metric", API_KEY)

            resultPopularMovie.postValue(ApiResponse.success(response))
        }

        return resultPopularMovie
    }
}
