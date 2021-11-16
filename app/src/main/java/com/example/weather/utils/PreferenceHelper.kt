package com.example.weather.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.weather.data.model.WeatherModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PreferenceHelper(context:Context) {
    val PREFERANCE_NAME = "Weather_Demo"
    val PREFERANCE_LATEST_WEATHER = "LATEST_WEATHER"
    val preference = context.getSharedPreferences(PREFERANCE_NAME, Context.MODE_PRIVATE)

    fun saveArrayList(list: List<String?>?, key: String?) {
        val editor: SharedPreferences.Editor = preference.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getArrayList(key: String?): List<String?>? {
        val gson = Gson()
        val json: String? = preference.getString(key, "")
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveLatestWeather(weatherData: WeatherModel) {
        val editor: SharedPreferences.Editor = preference.edit()
        val gson = Gson()
        val json: String = gson.toJson(weatherData)
        editor.putString(PREFERANCE_LATEST_WEATHER, json)
        editor.apply()
    }

    fun getLatestWeather(): WeatherModel? {
        val gson = Gson()
        val json: String? = preference.getString(PREFERANCE_LATEST_WEATHER, "")
        val type: Type = object : TypeToken<WeatherModel>() {}.type
        return gson.fromJson(json, type)
    }
}