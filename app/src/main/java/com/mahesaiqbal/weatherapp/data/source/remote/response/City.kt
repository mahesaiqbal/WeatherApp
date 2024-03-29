package com.mahesaiqbal.weatherapp.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    var coord: Coord,
    @SerializedName("country")
    var country: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("population")
    var population: Int,
    @SerializedName("timezone")
    var timezone: Int
)