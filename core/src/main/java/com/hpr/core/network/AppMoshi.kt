package com.hpr.core.network

import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object AppMoshi {
    fun build(): Moshi = Moshi.Builder().build()

    fun moshiConverter(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)
}