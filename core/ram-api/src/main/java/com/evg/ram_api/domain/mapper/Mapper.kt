package com.evg.ram_api.domain.mapper

import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterLocationDBO
import com.evg.database.domain.models.CharacterOriginDBO
import com.evg.database.domain.models.EpisodeDBO
import com.evg.ram_api.domain.models.CharacterResponse
import com.evg.ram_api.domain.models.EpisodeResponse

fun CharacterResponse.toCharacterDBO(): CharacterDBO {
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

fun EpisodeResponse.toEpisodeDBO(): EpisodeDBO {
    return EpisodeDBO(
        id = this.id,
        name = this.name,
        air_date = this.air_date,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
    )
}
