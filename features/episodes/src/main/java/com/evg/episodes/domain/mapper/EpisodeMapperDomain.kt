package com.evg.episodes.domain.mapper

import com.evg.episodes.domain.model.Character
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterFilterDB
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.EpisodeFilterDB
import com.evg.episodes.domain.model.CharacterGender
import com.evg.episodes.domain.model.CharacterLocation
import com.evg.episodes.domain.model.CharacterOrigin
import com.evg.episodes.domain.model.CharacterStatus
import com.evg.episodes.domain.model.Episode
import com.evg.episodes.domain.model.EpisodeFilter
import com.evg.ram_api.domain.models.CharacterResponse
import com.evg.ram_api.domain.models.EpisodeFilterDTO
import com.evg.ram_api.domain.models.EpisodeResponse

internal fun CharacterResponse.toCharacter() : Character {
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

fun EpisodeDBO.toEpisode(): Episode {
    return Episode(
        id = this.id,
        name = this.name,
        air_date = this.air_date,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
    )
}

fun EpisodeFilter.toEpisodeFilterDTO(): EpisodeFilterDTO {
    return EpisodeFilterDTO(
        name = this.name,
    )
}


fun EpisodeFilter.toEpisodeFilterDB(): EpisodeFilterDB {
    return EpisodeFilterDB(
        name = this.name,
    )
}

fun EpisodeResponse.toEpisode(): Episode {
    return Episode(
        id = this.id,
        name = this.name,
        air_date = this.air_date,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
    )
}