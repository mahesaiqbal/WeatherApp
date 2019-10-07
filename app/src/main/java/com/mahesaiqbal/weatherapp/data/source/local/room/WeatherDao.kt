package com.mahesaiqbal.weatherapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_entity")
    fun getWeatherForecast(): LiveData<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherForecast(weatherForecast: List<WeatherEntity>): LongArray
}