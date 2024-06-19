package com.evg.database.data.repository

import com.evg.database.data.storage.CharacterDatabase
import com.evg.database.data.storage.EpisodeDatabase
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterFilterDB
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.database.domain.mapper.toStringDB
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.EpisodeFilterDB

class DatabaseRepositoryImpl(
    private val characterDatabase: CharacterDatabase,
    private val episodeDatabase: EpisodeDatabase,
): DatabaseRepository {
    override fun getAllCharactersByPage(
        pageSize: Int,
        offset: Int,
        filter: CharacterFilterDB
    ): List<CharacterDBO> {
        return characterDatabase.characterDao.getAllCharactersByPage(
            pageSize = pageSize,
            offset = offset,
            name = filter.name,
            status = filter.status.toStringDB(),
            species  = filter.species,
            type = filter.type,
            gender = filter.gender.toStringDB(),
        )
    }

    override suspend fun insertCharacter(character: CharacterDBO) {
        characterDatabase.characterDao.insertCharacter(character = character)
    }

    override suspend fun insertCharacters(characters: List<CharacterDBO>) {
        characterDatabase.characterDao.insertCharacters(characters = characters)
    }

    override fun getAllEpisodesByPage(
        pageSize: Int,
        offset: Int,
        filter: EpisodeFilterDB
    ): List<EpisodeDBO> {
        return episodeDatabase.episodeDao.getAllEpisodesByPage(
            pageSize = pageSize,
            offset = offset,
            name = filter.name,
        )
    }

    override suspend fun insertEpisode(episode: EpisodeDBO) {
        episodeDatabase.episodeDao.insertEpisode(episode = episode)
    }

    override suspend fun insertEpisodes(episodes: List<EpisodeDBO>) {
        episodeDatabase.episodeDao.insertEpisodes(episodes = episodes)
    }
}