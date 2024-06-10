package com.evg.characters.domain.mapper

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterFilter
import com.evg.characters.domain.model.CharacterGender
import com.evg.characters.domain.model.CharacterLocation
import com.evg.characters.domain.model.CharacterOrigin
import com.evg.characters.domain.model.CharacterStatus
import com.evg.characters.domain.model.GenderType
import com.evg.characters.domain.model.StatusType
import com.evg.database.domain.models.CharacterDBO
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.models.GenderTypeDTO
import com.evg.ram_api.domain.models.StatusTypeDTO

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

fun CharacterFilter.toCharacterFilterDTO(): CharacterFilterDTO {
    return CharacterFilterDTO(
        name = this.name,
        status = when (this.status) {
            StatusType.ALIVE -> StatusTypeDTO.ALIVE
            StatusType.DEAD -> StatusTypeDTO.DEAD
            StatusType.UNKNOWN -> StatusTypeDTO.UNKNOWN
            else -> null
        },
        species = this.species,
        type = this.type,
        gender = when (this.gender) {
            GenderType.FEMALE -> GenderTypeDTO.FEMALE
            GenderType.MALE -> GenderTypeDTO.MALE
            GenderType.GENDERLESS -> GenderTypeDTO.GENDERLESS
            GenderType.UNKNOWN -> GenderTypeDTO.UNKNOWN
            else -> null
        },
    )
}
