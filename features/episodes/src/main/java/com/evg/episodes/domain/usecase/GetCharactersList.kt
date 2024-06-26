package com.evg.episodes.domain.usecase

import com.evg.episodes.domain.model.Character
import com.evg.episodes.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersList @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {
    fun invoke(urlsList: List<String>): Flow<List<Character>?> {
        return episodesRepository.getCharactersList(urlsList = urlsList)
    }
}