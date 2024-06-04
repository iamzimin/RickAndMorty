package com.evg.characters.domain.usecase

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.ram_api.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharacters @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun invoke(): Flow<List<Character>> {
        return charactersRepository.getAllCharacters()
    }
}