package com.evg.ram_api.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PageResponse<T>(
    val info: PageInfoResponse,
    val results: List<T>
)
