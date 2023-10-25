package com.hpr.data.dataSource.map

import com.hpr.data.db.MapDao
import com.hpr.data.model.CarsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataSourceLocalMap(
    private val mapDao: MapDao
) {

    suspend fun getAllCars() : List<CarsModel>{
        return mapDao.getAllCars().map { it.toModel() }
    }

    fun getAllCarsFlow() : Flow<List<CarsModel>>{
        return mapDao.getAllCarsFlow().map { it.map { map -> map.toModel() } }
    }

}