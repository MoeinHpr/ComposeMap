package com.hpr.map.domain

import androidx.lifecycle.ViewModel
import com.hpr.data.api.base.AppNetworkResponse
import com.hpr.data.base.BaseViewModel
import com.hpr.data.repository.map.MapRepository
import kotlinx.coroutines.flow.flow

class MapViewModel(
    private val mapRepository: MapRepository
) : BaseViewModel() {


    suspend fun getCars(){
        callEnqueue(mapRepository.getCarsNetwork(),
            onSuccess = {

            },
            onError = {

            })
    }
}