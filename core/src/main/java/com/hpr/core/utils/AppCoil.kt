package com.hpr.core.utils

import android.content.Context
import coil.Coil
import coil.ImageLoader
import javax.inject.Singleton

@Singleton
object AppCoil {

    fun build(context: Context) : ImageLoader =
        ImageLoader.Builder(context)
            .availableMemoryPercentage(0.8)
            .build()
}