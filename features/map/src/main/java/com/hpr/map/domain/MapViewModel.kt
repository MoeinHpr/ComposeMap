package com.hpr.map.domain

import androidx.lifecycle.ViewModel
import com.hpr.data.api.base.AppNetworkResponse
import com.hpr.data.repository.map.MapRepository

class MapViewModel(
    private val mapRepository: MapRepository
) : ViewModel() {

    init {

    }

    suspend fun getCars(){
        val request = mapRepository.getCarsNetwork()
        when(request){
            is AppNetworkResponse.Success -> {
                request.response.body()
            }
            is AppNetworkResponse.Error -> {
                if (request is AppNetworkResponse.Error.NetworkError){
                    request.statusCode
                }
                else if (request is AppNetworkResponse.Error.AppException){
                    request.message
                }
            }
        }
    }
}