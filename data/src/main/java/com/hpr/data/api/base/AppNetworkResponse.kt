package com.hpr.data.api.base

import retrofit2.Response

sealed class AppNetworkResponse<out T> {

    data class Success<out T>(
        val response: T,
        val status: Boolean
    ) : AppNetworkResponse<T>()

    sealed class Error<T>() : AppNetworkResponse<T>() {

        data class NetworkError<T>(
            val statusCode: Int,
            val message: String
        ) : Error<T>()

        data class AppException<T>(
            val message: String
        ) : Error<T>()
    }


}
