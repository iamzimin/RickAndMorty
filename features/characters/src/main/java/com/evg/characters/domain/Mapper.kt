package com.evg.characters.domain

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterLocation
import com.evg.characters.domain.model.CharacterOrigin
import com.evg.ram_api.domain.models.CharactersResponse
import com.evg.ram_api.domain.models.LocationResponse
import com.evg.ram_api.domain.models.OriginResponse

internal fun CharactersResponse.toCharacter() : Character {
    return Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.toCharacterOrigin(),
        location = this.location.toCharacterOrigin(),
        image = this.image,
        episode = this.episode,
        url = this.url,
    )
}

internal fun OriginResponse.toCharacterOrigin(): CharacterOrigin {
    return CharacterOrigin(
        name = this.name,
        url = this.url,
    )
}

internal fun LocationResponse.toCharacterOrigin(): CharacterLocation {
    return CharacterLocation(
        name = this.name,
        url = this.url,
    )
}