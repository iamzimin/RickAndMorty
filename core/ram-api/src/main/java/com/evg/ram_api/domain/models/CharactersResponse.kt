package com.evg.ram_api.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginResponse,
    val location: CharacterLocationResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
)
