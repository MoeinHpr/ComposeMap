package com.hpr.core.network

import androidx.core.os.BuildCompat
import com.hpr.module.core.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object AppRetrofit {
    fun build(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL).
            addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
}