package com.evg.ram_api.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PageInfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)