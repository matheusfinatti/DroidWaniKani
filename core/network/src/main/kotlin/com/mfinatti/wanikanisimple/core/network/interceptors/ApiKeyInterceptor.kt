package com.mfinatti.wanikanisimple.core.network.interceptors

import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.Keys
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val preferences: SharedPreferences,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val apiKey = preferences.getString(Keys.API_KEY, "")

        val newRequest = if (apiKey.isNullOrBlank()) {
            request
        } else {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $apiKey")
                .build()
        }

        return chain.proceed(newRequest)
    }
}
