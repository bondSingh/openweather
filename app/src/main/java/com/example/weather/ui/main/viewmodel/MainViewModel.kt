package com.example.weather.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weather.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import com.example.weather.utils.Resource

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getWeather(city:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getWeather(city)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getCurrentLocationWeather(latitude: Double, longitude: Double) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getCurrentLocationWeather(latitude, longitude)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}