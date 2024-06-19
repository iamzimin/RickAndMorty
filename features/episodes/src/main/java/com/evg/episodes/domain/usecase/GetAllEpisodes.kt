package com.evg.episodes.domain.usecase

import androidx.paging.PagingData
import com.evg.episodes.domain.model.Episode
import com.evg.episodes.domain.model.EpisodeFilter
import com.evg.episodes.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllEpisodes @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {
    fun invoke(filter: EpisodeFilter): Flow<PagingData<Episode>> {
        return episodesRepository.getAllEpisodes(filter = filter)
    }
}