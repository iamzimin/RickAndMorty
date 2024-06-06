package com.evg.database.data.repository

import com.evg.database.data.storage.CharacterDatabase
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(
    private val characterDatabase: CharacterDatabase
): DatabaseRepository {
    override fun getAllCharactersByPage(pageSize: Int, offset: Int): List<CharacterDBO> {
        return characterDatabase.characterDao.getAllCharactersByPage(pageSize = pageSize, offset = offset)
    }

    override suspend fun insertCharacter(character: CharacterDBO) {
        characterDatabase.characterDao.insertCharacter(character = character)
    }

    override suspend fun insertCharacters(characters: List<CharacterDBO>) {
        characterDatabase.characterDao.insertCharacters(characters = characters)
    }
}