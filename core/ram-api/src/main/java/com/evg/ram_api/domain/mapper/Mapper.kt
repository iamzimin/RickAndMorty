package com.evg.ram_api.domain.mapper

import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterLocationDBO
import com.evg.database.domain.models.CharacterOriginDBO
import com.evg.ram_api.domain.models.CharactersResponse

fun CharactersResponse.toCharacterDBO(): CharacterDBO {
    return CharacterDBO(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = CharacterOriginDBO(
            name = this.origin.name,
            url = this.origin.url,
        ),
        location = CharacterLocationDBO(
            name = this.location.name,
            url = this.location.url,
        ),
        image = this.image,
        episode = this.episode,
        url = this.url
    )
}
