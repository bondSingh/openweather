package com.example.weather.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getWeather(city: String) = apiService.getWeather(city)

    suspend fun getCurrentLocationWeather(latitude: Double, longitude: Double) =
        apiService.getCurrentLocationWeather(latitude, longitude)
}