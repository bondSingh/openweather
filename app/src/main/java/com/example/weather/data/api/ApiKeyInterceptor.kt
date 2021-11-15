package com.example.weather.data.api

import com.example.weather.utils.Util
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.HttpUrl
import okhttp3.Request

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url: HttpUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("APPID", Util.API_KEY)
            .addQueryParameter("units", Util.MEASURE_SYSTEM)
            .build()
        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
