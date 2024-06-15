package com.evg.characters.domain.usecase

import javax.inject.Inject

data class CharactersUseCases @Inject constructor(
    val getAllCharacters: GetAllCharacters,
    val getCharacterById: GetCharacterById,
    val getEpisodesList: GetEpisodesList,
)