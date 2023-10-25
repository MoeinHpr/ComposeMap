package com.hpr.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
object AppInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request().newBuilder()
                    .addHeader("Cache-control", "no-cache")
                    .build()
            )
        }
    }

}