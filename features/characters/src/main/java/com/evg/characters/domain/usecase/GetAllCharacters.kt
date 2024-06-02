package com.evg.characters.domain.usecase

import com.evg.characters.domain.repository.CharactersRepository

class GetAllCharacters(
    private val charactersRepository: CharactersRepository
) {

}