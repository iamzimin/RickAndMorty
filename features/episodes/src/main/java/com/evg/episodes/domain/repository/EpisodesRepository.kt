package com.evg.episodes.domain.repository

import androidx.paging.PagingData
import com.evg.episodes.domain.model.Character
import com.evg.episodes.domain.model.Episode
import com.evg.episodes.domain.model.EpisodeFilter
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun getAllEpisodes(filter: EpisodeFilter): Flow<PagingData<Episode>>
    fun getEpisodeById(id: Int): Flow<Episode?>
    fun getCharactersList(urlsList: List<String>): Flow<List<Character>?>
}