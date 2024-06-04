package com.evg.characters.domain.repository

import com.evg.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(): Flow<List<Character>>
}