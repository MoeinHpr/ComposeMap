package com.hpr.core.network

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
object AppOkHttpClient {

    fun build(appInterceptor: AppInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(appInterceptor)
            .readTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L , TimeUnit.SECONDS)
            .connectTimeout(15L , TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(100 , 30L , TimeUnit.SECONDS))
            .build()
}