package com.hpr.core.utils

import android.content.Context
import coil.ImageLoader
import coil.memory.MemoryCache
import javax.inject.Singleton

@Singleton
object AppCoil {

    fun build(context: Context) : ImageLoader =
        ImageLoader.Builder(context)
            .memoryCache(MemoryCache.Builder(context).maxSizePercent(0.8).build())
            .build()
}