package com.example.weather.data.repository

import com.example.weather.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getWeather(city: String) = apiHelper.getWeather(city)

    suspend fun getCurrentLocationWeather(latitude: Double, longitude: Double) =
        apiHelper.getCurrentLocationWeather(latitude, longitude)
}