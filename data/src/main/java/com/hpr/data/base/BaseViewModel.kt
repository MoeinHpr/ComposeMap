package com.hpr.data.base

import androidx.lifecycle.ViewModel
import com.hpr.data.api.AppNetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel

open class BaseViewModel : ViewModel() {

    suspend fun <T> callEnqueue(
        request: AppNetworkResponse<T>,
        onSuccess: suspend (T) -> Unit,
        onServerError: (AppNetworkResponse.Error.NetworkError<T>) -> Unit,
        onAppException: (AppNetworkResponse.Error.AppException<T>) -> Unit,
    ){

        when (request) {
            is AppNetworkResponse.Success ->
                onSuccess.invoke(
                    request.response
                )

            is AppNetworkResponse.Error.NetworkError ->
                onServerError.invoke(
                    AppNetworkResponse.Error.NetworkError(
                        request.statusCode, request.message
                    )
                )

            is AppNetworkResponse.Error.AppException ->
                onAppException.invoke(
                    AppNetworkResponse.Error.AppException(
                        request.message
                    )
                )
        }
    }

}