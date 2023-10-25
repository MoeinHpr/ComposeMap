package com.hpr.data.db

import androidx.room.Dao
import androidx.room.Query
import com.hpr.data.db.base.BaseDao
import com.hpr.data.model.CarsEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class MapDao : BaseDao<CarsEntity>() {

    @Query("SELECT * FROM CarsEntity")
    abstract suspend fun getAllCars() : List<CarsEntity>

    @Query("SELECT * FROM CarsEntity")
    abstract fun getAllCarsFlow() : Flow<List<CarsEntity>>

}