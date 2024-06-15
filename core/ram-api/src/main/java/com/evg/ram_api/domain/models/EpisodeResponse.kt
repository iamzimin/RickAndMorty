package com.evg.ram_api.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
)
