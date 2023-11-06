package com.hpr.map.domain

import androidx.lifecycle.viewModelScope
import com.hpr.data.base.BaseViewModel
import com.hpr.data.repository.map.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository,
) : BaseViewModel() {

    fun carsListFlow() = mapRepository.getCarsFlowLocal()

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch {
            carsListFlow().collect {
                if (it.isEmpty()) {
                    callEnqueue(
                        request = {
                            mapRepository.getCarsNetwork()
                        },
                        onSuccess = { carsModelList ->
                            mapRepository.insertAllCarsLocal(carsModelList)
                        },
                        onServerError = {
                            val a = it.message
                        },
                        onAppException = {
                            val b = it
                        })
                }
            }

        }

    }
}