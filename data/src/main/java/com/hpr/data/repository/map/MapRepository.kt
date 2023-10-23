package com.hpr.data.repository.map

import com.hpr.data.api.base.AppNetworkResponse
import com.hpr.data.model.CarsModel
import kotlinx.coroutines.flow.Flow

interface MapRepository {

    suspend fun getCarsNetwork() : AppNetworkResponse<List<CarsModel>>

    suspend fun getCarsLocal() : List<CarsModel>

    fun getCarsFlowLocal() : Flow<List<CarsModel>>
}