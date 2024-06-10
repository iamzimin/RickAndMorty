package com.evg.ram_api.domain.repository

import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.models.PageResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getAllCharactersByPage(page: Int, filter: CharacterFilterDTO): Response<PageResponse<CharactersResponse>>
}