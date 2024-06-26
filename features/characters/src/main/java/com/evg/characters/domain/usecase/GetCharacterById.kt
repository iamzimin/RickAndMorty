package com.evg.characters.domain.usecase

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterById @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun invoke(id: Int): Flow<Character?> {
        return charactersRepository.getCharacterById(id = id)
    }
}