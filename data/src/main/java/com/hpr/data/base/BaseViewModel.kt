package com.hpr.data.base

import androidx.lifecycle.ViewModel
import com.hpr.data.api.base.AppNetworkResponse

class BaseViewModel : ViewModel() {

    fun <T>callEnqueue(
        request : AppNetworkResponse<T>,
        onSuccess : (AppNetworkResponse.Success<T> ) -> Unit,
        onError : (AppNetworkResponse.Error<T> ) -> Unit
    ){
        
    }

}