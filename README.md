# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* This is for study purpose. It have a simple sample codebase doing the following:
-MVVM architecture
-DayNight Theme
-Handling portrait and landscape mode of the mobile
-Making api call using Retrofit and coroutine
-Using interceptor to add the common headers and log the request and response
-Using some extension functions to make the codebase more readable

### How do I get set up? ###

* You can simply clone the project and open it in Android Studio.
* No external dependency required.
* Since this API-subscription(openWeatherMap) is a freemium subscription there is a limitation on number of calls possible


### Who do I talk to? ###

* 1202satish@gmail.com
[LinkedIn](https://www.linkedin.com/in/satish-singh-96856762/)

### References ###
* using openWeatherMap api to get the data [openWeatherMap](https://openweathermap.org/ )

*Project Structure
|-- AndroidManifest.xml
|-- ic_launcher-playstore.png
|-- java
|   `-- com
|       `-- example
|           `-- weather
|               |-- data
|               |   |-- api
|               |   |   |-- ApiHelper.kt
|               |   |   |-- ApiKeyInterceptor.kt
|               |   |   |-- ApiService.kt
|               |   |   `-- RetrofitBuilder.kt
|               |   |-- model
|               |   |   `-- WeatherModel.kt
|               |   `-- repository
|               |       `-- MainRepository.kt
|               |-- ui
|               |   |-- base
|               |   |   `-- ViewModelFactory.kt
|               |   `-- main
|               |       |-- view
|               |       |   `-- MainActivity.kt
|               |       `-- viewmodel
|               |           `-- MainViewModel.kt
|               `-- utils
|                   |-- Resource.kt
|                   |-- Status.kt
|                   `-- Util.kt
`-- res
    |-- drawable
    |   |-- bg_gradient.xml
    |   |-- humidity.png
    |   |-- ic_edit_location.xml
    |   |-- ic_launcher_background.xml
    |   |-- ic_launcher_foreground.xml
    |   |-- ic_openweather.png
    |   |-- ic_search.xml
    |   |-- info.png
    |   |-- pressure.png
    |   |-- sunrise.png
    |   |-- sunset.png
    |   `-- wind.png
    |-- drawable-anydpi-v24
    |-- drawable-hdpi
    |-- drawable-mdpi
    |-- drawable-v24
    |-- drawable-xhdpi
    |-- drawable-xxhdpi
    |-- layout
    |   `-- main_activity.xml
    |-- layout-land
    |   `-- main_activity.xml
    |-- mipmap-anydpi-v26
    |   |-- ic_launcher.xml
    |   `-- ic_launcher_round.xml
    |-- mipmap-hdpi
    |   |-- ic_launcher.png
    |   `-- ic_launcher_round.png
    |-- mipmap-mdpi
    |   |-- ic_launcher.png
    |   `-- ic_launcher_round.png
    |-- mipmap-xhdpi
    |   |-- ic_launcher.png
    |   `-- ic_launcher_round.png
    |-- mipmap-xxhdpi
    |   |-- ic_launcher.png
    |   `-- ic_launcher_round.png
    |-- mipmap-xxxhdpi
    |   |-- ic_launcher.png
    |   `-- ic_launcher_round.png
    |-- values
    |   |-- colors.xml
    |   |-- strings.xml
    |   `-- themes.xml
    |-- values-land
    `-- values-night
        `-- themes.xml
