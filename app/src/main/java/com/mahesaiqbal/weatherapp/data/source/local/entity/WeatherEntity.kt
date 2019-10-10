package com.mahesaiqbal.weatherapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_entity")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "city_name")
    var cityName: String,
    @ColumnInfo(name = "datetime")
    var datetime: Int,
    @ColumnInfo(name = "temp_day")
    var tempDay: Double,
    @ColumnInfo(name = "weather_main")
    var weatherMain: String,
    @ColumnInfo(name = "weather_icon")
    var weatherIcon: String
)