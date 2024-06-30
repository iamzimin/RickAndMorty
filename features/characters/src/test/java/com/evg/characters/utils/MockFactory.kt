package com.evg.characters.utils

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterGender
import com.evg.characters.domain.model.CharacterLocation
import com.evg.characters.domain.model.CharacterOrigin
import com.evg.characters.domain.model.CharacterStatus
import com.evg.characters.domain.model.Episode

object MockFactory {

    fun createMockCharacter(id: Int): Character {
        return Character(
            id = id,
            name = "Name $id",
            status = CharacterStatus.ALIVE,
            species = "Species $id",
            type = "",
            gender = CharacterGender.MALE,
            origin = CharacterOrigin(
                name = "Origin $id",
                url = "https://rickandmortyapi.com/api/origin$id"
            ),
            location = CharacterLocation(
                name = "Location $id",
                url = "https://rickandmortyapi.com/api/location$id"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar$id.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/$id",
                "https://rickandmortyapi.com/api/episode/$id",
            ),
            url = "https://rickandmortyapi.com/api/character/$id"
        )
    }

    fun createMockEpisode(id: Int): Episode {
        return Episode(
            id = id,
            name = "Name $id",
            air_date = "November 29, 2016",
            episode = "E1S2",
            characters = listOf(
                "https://rickandmortyapi.com/api/character/1",
                "https://rickandmortyapi.com/api/character/2",
            ),
            url = "https://rickandmortyapi.com/api/character/$id",
        )
    }
}