package com.evg.ram_api.models

import kotlinx.serialization.Serializable

@Serializable
data class OriginResponse(
    val name: String,
    val url: String,
)
