package com.hpr.data.api

import com.hpr.data.api.base.AppNetworkResponse
import com.hpr.data.model.CarsModel
import retrofit2.Response
import retrofit2.http.GET

interface MapApi {

    @GET("codingtask/cars")
    suspend fun getCars() : Response<List<CarsModel>>

}