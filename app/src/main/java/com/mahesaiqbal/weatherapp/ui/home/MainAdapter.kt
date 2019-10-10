package com.mahesaiqbal.weatherapp.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mahesaiqbal.weatherapp.R
import com.mahesaiqbal.weatherapp.data.source.local.entity.WeatherEntity
import com.mahesaiqbal.weatherapp.ui.home.MainAdapter.MainViewHolder
import kotlinx.android.synthetic.main.item_weather_forecast.view.*

class MainAdapter(var activity: Activity, var dailyForecast: List<WeatherEntity>) : Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder
            = MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather_forecast, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(dailyForecast[position])
    }

    override fun getItemCount(): Int = dailyForecast.size

    inner class MainViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(weather: WeatherEntity) {
            itemView.tv_day.text = weather.datetime
            itemView.tv_weather_condition.text = "${weather.tempDay}°"

            Glide.with(activity)
                .load("http://openweathermap.org/img/wn/${weather.weatherIcon}@2x.png")
                .into(itemView.img_weather_condition)
        }
    }
}
