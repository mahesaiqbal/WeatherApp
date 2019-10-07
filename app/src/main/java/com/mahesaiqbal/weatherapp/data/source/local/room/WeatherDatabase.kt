package com.mahesaiqbal.weatherapp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity

@Database(
    entities = arrayOf(WeatherEntity::class),
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        private var INSTANCE: WeatherDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): WeatherDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDatabase::class.java, "weather.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }

}
