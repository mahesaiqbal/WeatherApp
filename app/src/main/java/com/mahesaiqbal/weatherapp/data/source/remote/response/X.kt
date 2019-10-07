package com.mahesaiqbal.weatherapp.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class X(
    @SerializedName("clouds")
    var clouds: Int,
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("humidity")
    var humidity: Int,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("rain")
    var rain: Double,
    @SerializedName("speed")
    var speed: Double,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int,
    @SerializedName("temp")
    var temp: Temp,
    @SerializedName("weather")
    var weather: List<Weather>
)