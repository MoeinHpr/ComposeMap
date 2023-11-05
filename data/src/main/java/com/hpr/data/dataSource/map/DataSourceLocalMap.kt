package com.hpr.data.dataSource.map

import com.hpr.data.db.MapDao
import com.hpr.data.model.CarsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataSourceLocalMap @Inject constructor(
    private val mapDao: MapDao
) {

    suspend fun insertAllCars(cars : List<CarsModel>){
        mapDao.insertAllCars(cars.map { it.toEntity() })
    }

    suspend fun getAllCars() : List<CarsModel>{
        return mapDao.getAllCars().map { it.toModel() }
    }

    fun getAllCarsFlow() : Flow<List<CarsModel>>{
        return mapDao.getAllCarsFlow().map { it.map { map -> map.toModel() } }
    }

}