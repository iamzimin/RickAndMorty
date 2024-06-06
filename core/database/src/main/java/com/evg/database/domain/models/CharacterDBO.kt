package com.evg.database.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class CharacterDBO(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginDBO,
    val location: CharacterLocationDBO,
    val image: String,
    val episode: List<String>,
    val url: String,
)

@Serializable
data class CharacterOriginDBO(
    val name: String,
    val url: String,
)

@Serializable
data class CharacterLocationDBO(
    val name: String,
    val url: String,
)