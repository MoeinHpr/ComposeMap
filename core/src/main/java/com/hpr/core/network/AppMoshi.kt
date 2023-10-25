package com.hpr.core.network

import com.squareup.moshi.Moshi
import javax.inject.Singleton

@Singleton
object AppMoshi {
    fun build(): Moshi = Moshi.Builder().build()
}