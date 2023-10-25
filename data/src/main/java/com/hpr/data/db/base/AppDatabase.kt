package com.hpr.data.db.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hpr.data.db.MapDao
import com.hpr.data.model.CarsEntity

@Database(
    entities = [
        CarsEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun mapDao() : MapDao
}