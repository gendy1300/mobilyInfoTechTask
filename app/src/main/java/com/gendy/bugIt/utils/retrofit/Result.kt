package com.gendy.bugIt.utils.retrofit

sealed class ApiResult<out T> {
    data object Loading : ApiResult<Nothing>()

    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val exception: Exception? = null,
        val didErrorParse: Boolean? = null
    ) : ApiResult<Nothing>()

    data object NoInternetConnection : ApiResult<Nothing>()
}





