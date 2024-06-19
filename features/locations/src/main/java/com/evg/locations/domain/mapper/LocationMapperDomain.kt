package com.evg.locations.domain.mapper

import com.evg.locations.domain.model.Character
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.CharacterFilterDB
import com.evg.database.domain.models.GenderTypeDB
import com.evg.database.domain.models.LocationDBO
import com.evg.database.domain.models.LocationFilterDB
import com.evg.database.domain.models.StatusTypeDB
import com.evg.locations.domain.model.CharacterGender
import com.evg.locations.domain.model.CharacterLocation
import com.evg.locations.domain.model.CharacterOrigin
import com.evg.locations.domain.model.CharacterStatus
import com.evg.locations.domain.model.Location
import com.evg.locations.domain.model.LocationFilter
import com.evg.ram_api.domain.models.CharacterFilterDTO
import com.evg.ram_api.domain.models.CharacterResponse
import com.evg.ram_api.domain.models.EpisodeResponse
import com.evg.ram_api.domain.models.GenderTypeDTO
import com.evg.ram_api.domain.models.LocationFilterDTO
import com.evg.ram_api.domain.models.LocationResponse
import com.evg.ram_api.domain.models.StatusTypeDTO

internal fun LocationResponse.toLocation() : Location {
    return Location(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension,
        residents = this.residents,
        url = this.url,
    )
}

internal fun LocationDBO.toLocation() : Location {
    return Location(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension,
        residents = this.residents,
        url = this.url,
    )
}

internal fun LocationFilter.toLocationFilterDTO() : LocationFilterDTO {
    return LocationFilterDTO(
        name = this.name,
        type = this.type,
        dimension = this.dimension,
    )
}

internal fun LocationFilter.toLocationFilterDB() : LocationFilterDB {
    return LocationFilterDB(
        name = this.name,
        type = this.type,
        dimension = this.dimension,
    )
}

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