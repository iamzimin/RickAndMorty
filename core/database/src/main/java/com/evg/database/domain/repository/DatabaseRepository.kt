package com.evg.database.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterFilterDB
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.EpisodeFilterDB
import com.evg.database.domain.models.LocationDBO
import com.evg.database.domain.models.LocationFilterDB

interface DatabaseRepository {
    //Characters
    fun getAllCharactersByPage(
        pageSize: Int,
        offset: Int,
        filter: CharacterFilterDB,
    ): List<CharacterDBO>
    suspend fun insertCharacter(character: CharacterDBO)
    suspend fun insertCharacters(characters: List<CharacterDBO>)
    suspend fun getCharacterById(id: Int): CharacterDBO?

    //Episodes
    fun getAllEpisodesByPage(
        pageSize: Int,
        offset: Int,
        filter: EpisodeFilterDB,
    ): List<EpisodeDBO>
    suspend fun insertEpisode(episode: EpisodeDBO)
    suspend fun insertEpisodes(episodes: List<EpisodeDBO>)
    suspend fun getEpisodeById(id: Int): EpisodeDBO?

    //Locations
    fun getAllLocationsByPage(
        pageSize: Int,
        offset: Int,
        filter: LocationFilterDB,
    ): List<LocationDBO>
    suspend fun insertLocation(location: LocationDBO)
    suspend fun insertLocations(locations: List<LocationDBO>)
    suspend fun getLocationById(id: Int): LocationDBO?
}