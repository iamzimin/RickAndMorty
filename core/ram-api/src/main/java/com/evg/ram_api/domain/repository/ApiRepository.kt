package com.evg.ram_api.domain.repository

import com.evg.ram_api.domain.models.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getAllCharacters(): Flow<List<CharactersResponse>>
}