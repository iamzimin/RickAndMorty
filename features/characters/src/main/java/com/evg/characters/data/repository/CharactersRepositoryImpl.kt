package com.evg.characters.data.repository

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.domain.toCharacter
import com.evg.ram_api.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiRepository: ApiRepository
): CharactersRepository {
    override fun getAllCharacters(): Flow<List<Character>> {
        return apiRepository.getAllCharacters().map { elem ->
            elem.map {
                it.toCharacter()
            }
        }
    }
}