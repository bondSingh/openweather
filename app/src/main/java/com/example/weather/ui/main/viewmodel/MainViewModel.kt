package com.example.weather.ui.main.viewmodel

import androidx.lifecycle.*
import com.example.weather.data.model.Weather
import com.example.weather.data.model.WeatherModel
import com.example.weather.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import com.example.weather.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val weatherData = MutableLiveData<Resource<WeatherModel>>()
    val weatherLiveData: LiveData<Resource<WeatherModel>> = weatherData

    fun getWeather(city:String) = viewModelScope.launch {
        weatherData.value = Resource.loading(data = null)
        try {
            val weatherDataResponse = mainRepository.getWeather(city)
            weatherData.value = Resource.success(data = weatherDataResponse)
        } catch (exception: Exception){
            weatherData.value = Resource.error(data = null, message = exception.message?:"Error Occurred!")
        }
    }

    fun getCurrentLocationWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        weatherData.value = Resource.loading(data = null)
        try {
            val weatherDataResponse = mainRepository.getCurrentLocationWeather(latitude, longitude)
            weatherData.value = Resource.success(data = weatherDataResponse)
        } catch (exception: Exception){
            weatherData.value = Resource.error(data = null, message = exception.message?:"Error Occurred!")
        }
    }
}