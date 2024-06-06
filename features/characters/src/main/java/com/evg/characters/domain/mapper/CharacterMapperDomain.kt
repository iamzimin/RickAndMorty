package com.evg.characters.domain.mapper

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterGender
import com.evg.characters.domain.model.CharacterLocation
import com.evg.characters.domain.model.CharacterOrigin
import com.evg.characters.domain.model.CharacterStatus
import com.evg.database.domain.models.CharacterDBO
import com.evg.ram_api.domain.models.CharactersResponse

internal fun CharactersResponse.toCharacter() : Character {
    return Character(
        id = this.id,
        name = this.name,
        status = when (this.status) {
            "Alive" -> CharacterStatus.ALIVE
            "Dead" -> CharacterStatus.DEAD
            else -> CharacterStatus.UNKNOWN
        },
        species = this.species,
        type = this.type,
        gender = when (this.gender) {
            "Female" -> CharacterGender.FEMALE
            "Male" -> CharacterGender.MALE
            "Genderless" -> CharacterGender.GENDERLESS
            else -> CharacterGender.UNKNOWN
        },
        origin = CharacterOrigin(
            name = this.origin.name,
            url = this.origin.url,
        ),
        location = CharacterLocation(
            name = this.location.name,
            url = this.location.url,
        ),
        image = this.image,
        episode = this.episode,
        url = this.url,
    )
}

internal fun CharacterDBO.toCharacter() : Character {
    return Character(
        id = this.id,
        name = this.name,
        status = when (this.status) {
            "Alive" -> CharacterStatus.ALIVE
            "Dead" -> CharacterStatus.DEAD
            else -> CharacterStatus.UNKNOWN
        },
        species = this.species,
        type = this.type,
        gender = when (this.gender) {
            "Female" -> CharacterGender.FEMALE
            "Male" -> CharacterGender.MALE
            "Genderless" -> CharacterGender.GENDERLESS
            else -> CharacterGender.UNKNOWN
        },
        origin = CharacterOrigin(
            name = this.origin.name,
            url = this.origin.url,
        ),
        location = CharacterLocation(
            name = this.location.name,
            url = this.location.url,
        ),
        image = this.image,
        episode = this.episode,
        url = this.url,
    )
}