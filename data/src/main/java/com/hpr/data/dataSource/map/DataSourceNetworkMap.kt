package com.hpr.data.dataSource.map

import com.hpr.data.api.MapApi
import com.hpr.data.api.base.AppNetworkResponse
import com.hpr.data.model.CarsModel

class DataSourceNetworkMap(
    private val mapApi: MapApi
) {
    suspend fun getCars() : AppNetworkResponse<List<CarsModel>>{
        return mapApi.getCars()
    }
}