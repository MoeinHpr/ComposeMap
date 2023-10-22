package com.hpr.core.network

import com.squareup.moshi.Moshi

object AppMoshi {
    fun build(): Moshi = Moshi.Builder().build()
}