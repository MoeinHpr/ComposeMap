package com.hpr.data.db.base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun <T>insert(data : T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun <T>insert(data : List<T>)

    @Delete
    abstract suspend fun <T>delete(data : T)

    @Delete
    abstract suspend fun <T>deleteAll()

    suspend fun <T>safeInsert(data : T?){
        if (data != null)
            insert(data)
    }

}