package com.gendy.bugIt.utils.retrofit

sealed class Result<out T> {
    data object Loading : Result<Nothing>()

    data class Success<T>(val data: T) : Result<T>()
    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val exception: Exception? = null,
        val didErrorParse: Boolean? = null
    ) : Result<Nothing>()

    data object NoInternetConnection : Result<Nothing>()
}





