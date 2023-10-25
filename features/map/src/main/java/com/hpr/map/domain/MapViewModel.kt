package com.hpr.map.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpr.data.base.BaseViewModel
import com.hpr.data.repository.map.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : BaseViewModel() {

    fun _carsListFlow() = mapRepository.getCarsFlowLocal()

    init {
        getCars()
    }

    fun getCars() {
        viewModelScope.launch {
            callEnqueue(
                request = mapRepository.getCarsNetwork(),
                onSuccess = {
                    mapRepository.insertAllCarsLocal(it)
                },
                onServerError = {
                   val a=  it.message
                },
                onAppException = {
                    val b = it
                })
        }

    }
}