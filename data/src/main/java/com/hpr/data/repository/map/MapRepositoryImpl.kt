package com.hpr.data.repository.map

import com.hpr.data.api.base.AppNetworkResponse
import com.hpr.data.dataSource.map.DataSourceLocalMap
import com.hpr.data.dataSource.map.DataSourceNetworkMap
import com.hpr.data.model.CarsModel
import kotlinx.coroutines.flow.Flow

class MapRepositoryImpl(
    private val dataSourceLocalMap: DataSourceLocalMap,
    private val dataSourceNetworkMap: DataSourceNetworkMap
) : MapRepository {
    override suspend fun getCarsNetwork(): AppNetworkResponse<List<CarsModel>> {
        return dataSourceNetworkMap.getCars()
    }

    override suspend fun getCarsLocal(): List<CarsModel> {
        return dataSourceLocalMap.getAllCars()
    }

    override fun getCarsFlowLocal(): Flow<List<CarsModel>> {
        return dataSourceLocalMap.getAllCarsFlow()
    }
}