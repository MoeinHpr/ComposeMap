package com.hpr.data.dataSource.base

import com.hpr.data.api.AppNetworkResponse
import retrofit2.Response

open class BaseDataSourceNetwork {

    fun <T> checkResult(
        request: Response<T>,
    ): AppNetworkResponse<T> {
        return try {
            if (request.isSuccessful) {
                AppNetworkResponse.Success(request.body()!!, true)
            } else {
                AppNetworkResponse.Error.NetworkError(request.code(), request.message())
            }
        } catch (e: Exception) {
            AppNetworkResponse.Error.AppException(e.message ?: "")
        }

    }
}