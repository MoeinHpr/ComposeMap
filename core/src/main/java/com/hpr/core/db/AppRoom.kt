package com.hpr.core.db

import android.content.Context
import androidx.room.Room
import com.hpr.data.db.AppDatabase
import javax.inject.Singleton

@Singleton
object AppRoom {
    fun build(context: Context) : AppDatabase =
        Room.databaseBuilder(context , AppDatabase::class.java , "app-database").build()
}