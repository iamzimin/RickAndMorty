package com.evg.characters.domain.usecase

import com.evg.characters.domain.model.Episode
import com.evg.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodesList @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    operator fun invoke(urlsList: List<String>): Flow<List<Episode>?> {
        return charactersRepository.getEpisodesList(urlsList = urlsList)
    }
}