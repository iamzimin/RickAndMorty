package com.evg.database.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.evg.database.domain.models.CharacterDBO

interface DatabaseRepository {
    fun getAllCharactersByPage(pageSize: Int, offset: Int): List<CharacterDBO>
    suspend fun insertCharacter(character: CharacterDBO)
    suspend fun insertCharacters(characters: List<CharacterDBO>)
}