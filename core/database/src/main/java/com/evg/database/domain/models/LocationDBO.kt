package com.evg.database.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationDBO(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
)