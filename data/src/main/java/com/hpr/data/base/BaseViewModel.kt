package com.hpr.data.base

import androidx.lifecycle.ViewModel
import com.hpr.data.api.AppNetworkChecker
import com.hpr.data.api.AppNetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
) : ViewModel() {

    @Inject
    lateinit var appNetworkChecker: AppNetworkChecker

    suspend fun <T> callEnqueue(
        request: suspend () -> AppNetworkResponse<T>,
        onSuccess: suspend (T) -> Unit,
        onServerError: (AppNetworkResponse.Error.NetworkError<T>) -> Unit,
        onAppException: (AppNetworkResponse.Error.AppException<T>) -> Unit,
    ) {
        if (appNetworkChecker.isNetworkAvailable()) {
            when (val result = request.invoke()) {
                is AppNetworkResponse.Success ->
                    onSuccess.invoke(
                        result.response
                    )

                is AppNetworkResponse.Error.NetworkError ->
                    onServerError.invoke(
                        AppNetworkResponse.Error.NetworkError(
                            result.statusCode, result.message
                        )
                    )

                is AppNetworkResponse.Error.AppException ->
                    onAppException.invoke(
                        AppNetworkResponse.Error.AppException(
                            result.message
                        )
                    )
            }
        } else {
            onAppException.invoke(
                AppNetworkResponse.Error.AppException("No Internet!")
            )

        }

    }

}