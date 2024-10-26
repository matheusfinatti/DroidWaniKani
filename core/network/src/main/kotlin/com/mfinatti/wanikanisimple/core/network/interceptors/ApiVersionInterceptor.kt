package com.mfinatti.wanikanisimple.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiVersionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Wanikani-Revision", "20170710")
            .build()

        return chain.proceed(newRequest)
    }
}
