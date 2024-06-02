package com.evg.characters.domain.repository

interface CharactersRepository {
    suspend fun getAllCharacters()
}