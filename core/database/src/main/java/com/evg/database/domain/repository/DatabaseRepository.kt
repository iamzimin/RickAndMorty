package com.evg.database.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterFilterDB
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.EpisodeFilterDB

interface DatabaseRepository {
    //Characters
    fun getAllCharactersByPage(
        pageSize: Int,
        offset: Int,
        filter: CharacterFilterDB,
    ): List<CharacterDBO>
    suspend fun insertCharacter(character: CharacterDBO)
    suspend fun insertCharacters(characters: List<CharacterDBO>)

    //Episodes
    fun getAllEpisodesByPage(
        pageSize: Int,
        offset: Int,
        filter: EpisodeFilterDB,
    ): List<EpisodeDBO>
    suspend fun insertEpisode(episode: EpisodeDBO)
    suspend fun insertEpisodes(episodes: List<EpisodeDBO>)
}