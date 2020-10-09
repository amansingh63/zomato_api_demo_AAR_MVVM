package com.amansingh63.zomatoapidemo.util.network

import com.amansingh63.zomatoapidemo.models.ApiError
import com.amansingh63.zomatoapidemo.models.ApiResult
import kotlinx.serialization.SerializationException
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLException

class RetrofitErrorUtil @Inject constructor(private val retrofit: Retrofit) {

    fun <T> parseError(response: Response<T>): ApiResult.Error {
        val converter =
            retrofit.responseBodyConverter<ApiError>(ApiError::class.java, arrayOfNulls(0))
        val error: ApiError
        try {
            error = if (response.errorBody() != null) {
                converter.convert(response.errorBody()!!)!!
            } else {
                ApiError(response.code(), response.message(), "")
            }
        } catch (error: Throwable) {
            Timber.e(error)
            var statusCode = 1002
            // keeping empty string as we cannot reference direct strings here
            var message = "Unknown Error"
            error.message?.let {
                message = it
            }
            if (response.code() != 0) {
                statusCode = response.code()
            }
            if (response.message().isNullOrEmpty()) {
                message = response.message()
            }
            return ApiResult.Error(ApiError(statusCode, message, ""))
        }

        return ApiResult.Error(error)
    }

    fun parseError(error: Throwable): ApiResult.Error {
        return when (error) {
            is UnknownHostException -> ApiResult.Error(ApiError(message = "Server Error"))
            is SocketTimeoutException -> ApiResult.Error(ApiError(message = "Server Timeout"))
            is ConnectException -> ApiResult.Error(ApiError(message = "Connection Refused"))
            is SerializationException -> ApiResult.Error(ApiError(message = "JSON Parsing Error"))
            is SSLException -> ApiResult.Error(ApiError(message = "SSL Security Error"))
            else -> {
                error.message?.let {
                    return ApiResult.Error(ApiError(message = it))
                } ?: run {
                    return ApiResult.Error(ApiError(message = "Unknown Error Occurred"))
                }
            }
        }
    }

}