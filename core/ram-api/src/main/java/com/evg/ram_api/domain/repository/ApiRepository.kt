package com.evg.ram_api.domain.repository

import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharacterResponse
import com.evg.ram_api.domain.models.EpisodeFilterDTO
import com.evg.ram_api.domain.models.EpisodeResponse
import com.evg.ram_api.domain.models.PageResponse

interface ApiRepository {
    //Characters
    suspend fun getAllCharactersByPage(page: Int, filter: CharacterFilterDTO): Response<PageResponse<CharacterResponse>>
    suspend fun getCharacterById(id: Int): Response<CharacterResponse>
    suspend fun getCharactersList(listIds: List<Int>): Response<List<CharacterResponse>>

    //Episodes
    suspend fun getAllEpisodesByPage(page: Int, filter: EpisodeFilterDTO): Response<PageResponse<EpisodeResponse>>
    suspend fun getEpisodeById(id: Int): Response<EpisodeResponse>
    suspend fun getEpisodesList(listIds: List<Int>): Response<List<EpisodeResponse>>

    fun isInternetAvailable(): Boolean
}