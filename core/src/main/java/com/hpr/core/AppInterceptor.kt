package com.hpr.core

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit


class AppInterceptor : Interceptor , Authenticator {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request().newBuilder()
                    .addHeader("appId", "")
                    .build()
            )
        }
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }


}