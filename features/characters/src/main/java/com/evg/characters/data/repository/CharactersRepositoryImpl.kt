package com.evg.characters.data.repository

import com.evg.characters.domain.repository.CharactersRepository
import com.evg.ram_api.KtorClient
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): CharactersRepository {
    override suspend fun getAllCharacters() {
        TODO("Not yet implemented")
    }

}