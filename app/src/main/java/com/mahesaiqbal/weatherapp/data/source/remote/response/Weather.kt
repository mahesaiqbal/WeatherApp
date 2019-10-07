package com.mahesaiqbal.weatherapp.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String
)