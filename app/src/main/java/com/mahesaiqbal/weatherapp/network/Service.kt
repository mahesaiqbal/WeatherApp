package com.mahesaiqbal.weatherapp.network

import com.mahesaiqbal.weatherapp.data.source.remote.response.DailyForecastResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("daily")
    fun getDailyForecast(
        @Query("q") area: String,
        @Query("units") units: String,
        @Query("appid") appId: String): Observable<DailyForecastResponse>
}