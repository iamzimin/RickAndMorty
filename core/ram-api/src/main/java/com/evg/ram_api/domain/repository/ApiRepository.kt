package com.evg.ram_api.domain.repository

import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.models.EpisodeResponse
import com.evg.ram_api.domain.models.PageResponse

interface ApiRepository {
    suspend fun getAllCharactersByPage(page: Int, filter: CharacterFilterDTO): Response<PageResponse<CharactersResponse>>
    suspend fun getCharacterById(id: Int): Response<CharactersResponse>
    suspend fun getEpisodesList(listIds: List<Int>): Response<List<EpisodeResponse>>
}