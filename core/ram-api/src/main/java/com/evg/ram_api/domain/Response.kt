package com.evg.ram_api.domain

sealed interface Response<T> {
    data class Success<T>(val data: T): Response<T>
    data class Failure<T>(val exception: Exception): Response<T>

    fun onSuccess(block: (T) -> Unit): Response<T> {
        if (this is Success) block(data)
        return this
    }


    fun onFailure(block: (Exception) -> Unit): Response<T> {
        if (this is Failure) block(exception)
        return this
    }
}