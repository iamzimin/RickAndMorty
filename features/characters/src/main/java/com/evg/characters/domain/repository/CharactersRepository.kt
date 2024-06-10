package com.evg.characters.domain.repository

import androidx.paging.PagingData
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterFilter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(filter: CharacterFilter): Flow<PagingData<Character>>
}