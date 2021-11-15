package com.example.weather.ui.main.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.weather.R
import com.example.weather.data.api.ApiHelper
import com.example.weather.data.api.RetrofitBuilder
import com.example.weather.data.model.WeatherModel
import com.example.weather.databinding.MainActivityBinding
import com.example.weather.ui.base.ViewModelFactory
import com.example.weather.ui.main.viewmodel.MainViewModel
import com.example.weather.utils.Util.clickWithDebounce
import com.example.weather.utils.Util.hideKeyboard
import com.example.weather.utils.Util.showKeyboard
import com.example.weather.utils.Status
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainActivityBinding
    private lateinit var searchCity: String
    private val TAG = "satyLogs"
    var decimalFormat = DecimalFormat("##.#")
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

    }

    override fun onStart() {
        super.onStart()

        setupObservers()
        if(viewModel.weatherLiveData.value==null) {
            getLocation()
        }
        binding.icEditLocation?.clickWithDebounce {
            binding.addressContainer.visibility = View.GONE
            binding.searchContainer?.visibility = View.VISIBLE
            binding.icEditLocation?.visibility = View.GONE
            binding.addressEditText?.requestFocus()
            binding.addressEditText?.showKeyboard()
        }

        binding.icSearch?.clickWithDebounce {
            searchCity = binding.addressEditText?.text.toString()
            binding.addressEditText?.hideKeyboard()
            binding.addressContainer.visibility = View.VISIBLE
            binding.searchContainer?.visibility = View.GONE
            binding.icEditLocation?.visibility = View.VISIBLE
            viewModel.getWeather(searchCity)
            //setupObservers()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]


    }

    private fun setupObservers() {
        viewModel.weatherLiveData.observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d(TAG, "Status.SUCCESS")
                        binding.loader.visibility = View.GONE
                        resource.data?.let { weather -> updateWeatherView(weather) }
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "Status.ERROR")
                        if (resource.message?.contains("404") == true) {
                            Toast.makeText(
                                this,
                                getString(R.string.enter_valid_city),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.something_went_wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding.loader.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        Log.d(TAG, "Status.LOADING")
                        binding.loader.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    /*private fun getWeatherForCurrentLocation(location: Location) {
        viewModel.getCurrentLocationWeather(location.latitude, location.longitude).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d(TAG, "Status.SUCCESS")
                        binding.loader.visibility = View.GONE
                        resource.data?.let { weather -> updateWeatherView(weather) }
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "Status.ERROR")
                        if (resource.message?.contains("404") == true) {
                            Toast.makeText(
                                this,
                                getString(R.string.enter_valid_city),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.something_went_wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding.loader.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        Log.d(TAG, "Status.LOADING")
                        binding.loader.visibility = View.VISIBLE
                    }
                }
            }
        })
    }*/

    private fun updateWeatherView(weather: WeatherModel) {
        searchCity = weather.name
        binding.address.text = weather.name
        binding.updatedAt.text = "Updated at: " + SimpleDateFormat("hh:mm aa")
            .format(Date((weather.dt) * 1000))
        binding.status.text = weather.weather[0].description
        binding.temp.text = decimalFormat.format(weather.main.temp).toString() + "°C"
        binding.feelsLike.text =
            "Feels Like: " + decimalFormat.format(weather.main.feelsLike).toString() + "°C"
        binding.tempMax.text =
            "Max Temp:" + decimalFormat.format(weather.main.tempMax).toString() + "°C"
        binding.tempMin.text =
            "Min Temp:" + decimalFormat.format(weather.main.tempMin).toString() + "°C"
        binding.sunrise.text = SimpleDateFormat("hh:mm aa")
            .format(Date((weather.sys.sunrise) * 1000))
        binding.sunset.text = SimpleDateFormat("hh:mm aa")
            .format(Date((weather.sys.sunset) * 1000))
        binding.wind.text = weather.wind.speed.toString() + "m/s"
        binding.pressure.text = weather.main.pressure.toString() + "hPa"
        binding.humidity.text = weather.main.humidity.toString() + "%"

        binding.icWeather?.let {
            Glide.with(this)
                .load("https://openweathermap.org/img/wn/" + weather.weather[0].icon + "@4x.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(it)
        }

    }

    private fun getLocation() {
        Log.d(TAG, "getLocation")
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5000f, this)
    }

    override fun onLocationChanged(location: Location) {
        viewModel.getCurrentLocationWeather(location.latitude, location.longitude)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


}