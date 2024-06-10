package com.evg.database.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterFilterDB

interface DatabaseRepository {
    fun getAllCharactersByPage(
        pageSize: Int,
        offset: Int,
        filter: CharacterFilterDB,
    ): List<CharacterDBO>
    suspend fun insertCharacter(character: CharacterDBO)
    suspend fun insertCharacters(characters: List<CharacterDBO>)
}