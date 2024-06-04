package com.evg.characters.domain.repository

import androidx.paging.PagingData
import com.evg.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(): Flow<PagingData<Character>>
}