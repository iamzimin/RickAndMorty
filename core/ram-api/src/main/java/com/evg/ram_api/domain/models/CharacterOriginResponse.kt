package com.evg.ram_api.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterOriginResponse(
    val name: String,
    val url: String,
)
