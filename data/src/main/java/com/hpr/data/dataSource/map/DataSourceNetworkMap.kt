package com.hpr.data.dataSource.map

import com.hpr.data.api.AppNetworkResponse
import com.hpr.data.api.MapApi
import com.hpr.data.dataSource.base.BaseDataSourceNetwork
import com.hpr.data.model.CarsModel
import javax.inject.Inject

class DataSourceNetworkMap @Inject constructor(
    private val mapApi: MapApi
) : BaseDataSourceNetwork() {

    suspend fun getCars() : AppNetworkResponse<List<CarsModel>> {
         return checkResult(mapApi.getCars())
    }
}