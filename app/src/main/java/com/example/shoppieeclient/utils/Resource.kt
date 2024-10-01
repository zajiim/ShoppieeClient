package com.example.shoppieeclient.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
    //    class Success<T>(data: T?): Resource<T>(data)
    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}