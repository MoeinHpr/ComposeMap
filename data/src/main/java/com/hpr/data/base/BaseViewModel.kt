package com.hpr.data.base

import androidx.lifecycle.ViewModel
import com.hpr.data.api.base.AppNetworkResponse

open class BaseViewModel : ViewModel() {

    fun <T> callEnqueue(
        request: AppNetworkResponse<T>,
        onSuccess: (AppNetworkResponse.Success<T>) -> Unit,
        onError: (AppNetworkResponse.Error<T>) -> Unit,
    ) {
        when (request) {
            is AppNetworkResponse.Success ->
                onSuccess.invoke(
                    AppNetworkResponse.Success(
                        request.response,
                        true
                    )
                )

            is AppNetworkResponse.Error.NetworkError ->
                onError.invoke(
                    AppNetworkResponse.Error.NetworkError(
                        request.statusCode, request.message
                    )
                )

            is AppNetworkResponse.Error.AppException ->
                onError.invoke(
                    AppNetworkResponse.Error.AppException(
                        request.message
                    )
                )
        }
    }

}