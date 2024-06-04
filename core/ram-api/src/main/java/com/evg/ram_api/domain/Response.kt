package com.evg.ram_api.domain

sealed interface Response<T> {
    data class Success<T>(val data: T): Response<T>
    data class Failure<T>(val exception: Exception): Response<T>
}