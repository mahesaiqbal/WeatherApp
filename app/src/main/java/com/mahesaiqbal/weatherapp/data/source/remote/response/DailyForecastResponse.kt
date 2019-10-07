package com.mahesaiqbal.weatherapp.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class DailyForecastResponse(
    @SerializedName("city")
    var city: City,
    @SerializedName("cnt")
    var cnt: Int,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("list")
    var list: List<X>,
    @SerializedName("message")
    var message: Double
)