package com.example.weather.data.api;

import com.example.weather.data.model.WeatherModel;

import retrofit2.http.GET;
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String
    ): WeatherModel

    @GET("weather")
    suspend fun getCurrentLocationWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): WeatherModel
}
