package com.evg.database.domain.models

data class PageResult<T>(
    val data: List<T>,
    val prevKey: Int?,
    val nextKey: Int?
)