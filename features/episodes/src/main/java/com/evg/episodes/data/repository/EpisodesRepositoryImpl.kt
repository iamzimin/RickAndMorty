package com.evg.episodes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.evg.database.data.EpisodePageSourceLocal
import com.evg.episodes.domain.mapper.toCharacter
import com.evg.episodes.domain.mapper.toEpisode
import com.evg.episodes.domain.mapper.toEpisodeFilterDB
import com.evg.episodes.domain.mapper.toEpisodeFilterDTO
import com.evg.episodes.domain.model.Character
import com.evg.episodes.domain.model.Episode
import com.evg.episodes.domain.model.EpisodeFilter
import com.evg.episodes.domain.repository.EpisodesRepository
import com.evg.ram_api.data.EpisodePageSourceRemote
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val apiRepository: ApiRepository,
    private val episodesPageSourceLocal: EpisodePageSourceLocal,
    private val episodesPageSourceRemote: EpisodePageSourceRemote,
): EpisodesRepository {
    override fun getAllEpisodes(filter: EpisodeFilter): Flow<PagingData<Episode>> {
        if (apiRepository.isInternetAvailable()) {
            return Pager(
                PagingConfig(
                pageSize = 5,
            )
            ) { episodesPageSourceRemote.apply { this.filter = filter.toEpisodeFilterDTO() } }.flow.map { pagingData ->
                pagingData.map {
                    it.toEpisode()
                }
            }
        } else {
            return Pager(
                PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
            )
            ) { episodesPageSourceLocal.apply { this.filter = filter.toEpisodeFilterDB() } }.flow.map { pagingData ->
                pagingData.map {
                    it.toEpisode()
                }
            }
        }
    }

    override fun getEpisodeById(id: Int): Flow<Episode?> {
        return flow {
            when (val response = apiRepository.getEpisodeById(id = id)) {
                is Response.Success -> {
                    emit(response.data.toEpisode())
                }
                is Response.Failure -> {
                    emit(null)
                }
            }
        }
    }

    override fun getCharactersList(urlsList: List<String>): Flow<List<Character>?> {
        val listIds = urlsList.mapNotNull { url ->
            val matchResult = Regex("""/character/(\d+)""").find(url)
            matchResult?.groups?.get(1)?.value?.toIntOrNull()
        }

        return flow {
            when (val response = apiRepository.getCharactersList(listIds = listIds)) {
                is Response.Success -> {
                    emit(response.data.map { it.toCharacter() } )
                }
                is Response.Failure -> {
                    emit(null)
                }
            }
        }
    }
}