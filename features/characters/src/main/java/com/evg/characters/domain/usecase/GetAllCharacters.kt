package com.evg.characters.domain.usecase

import androidx.paging.PagingData
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.ram_api.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharacters @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun invoke(): Flow<PagingData<Character>> {
        return charactersRepository.getAllCharacters()
    }
}