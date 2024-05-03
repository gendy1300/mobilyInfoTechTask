package com.gendy.bugIt.utils.retrofit


import com.gendy.bugIt.utils.logDebug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ApiResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiResult.Success(apiCall.invoke())
            } catch (e: Exception) {
                logDebug(e.stackTraceToString())
                when (e) {
                    is HttpException -> {
                        ApiResult.Error(
                            code = e.code(),
                            message = e.message(),
                            exception = e,
                        )

                    }

                    is NoInternetConnectionException -> {
                        ApiResult.NoInternetConnection
                    }

                    else -> {
                        ApiResult.Error(exception = e)
                    }
                }
            }
        }
    }
}