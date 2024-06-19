package com.evg.resource.model.character

data class LocationUI(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
)
