package com.zero.synefiliya.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null, code: Int?) : NetworkResult<T>(data, message,code)
    class Loading<T> : NetworkResult<T>()
}
