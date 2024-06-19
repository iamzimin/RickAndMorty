package com.evg.database.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EpisodeDBO(
    @PrimaryKey val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
)
