package com.evg.locations.presentation.mapper

import com.evg.locations.domain.model.Character
import com.evg.locations.domain.model.CharacterGender
import com.evg.locations.domain.model.CharacterStatus
import com.evg.locations.domain.model.Location
import com.evg.ram_api.domain.mapper.toLocationDBO
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI
import com.evg.resource.model.character.EpisodeUI
import com.evg.resource.model.character.LocationUI

internal fun Character.toCharacterUI() : CharacterUI {
    return CharacterUI(
        id = this.id,
        name = this.name,
        status = when (this.status) {
            CharacterStatus.ALIVE -> CharacterStatusUI.ALIVE
            CharacterStatus.DEAD -> CharacterStatusUI.DEAD
            CharacterStatus.UNKNOWN -> CharacterStatusUI.UNKNOWN
        },
        species = this.species,
        type = this.type,
        gender = when (this.gender) {
            CharacterGender.FEMALE -> CharacterGenderUI.FEMALE
            CharacterGender.MALE -> CharacterGenderUI.MALE
            CharacterGender.GENDERLESS -> CharacterGenderUI.GENDERLESS
            CharacterGender.UNKNOWN -> CharacterGenderUI.UNKNOWN
        },
        origin = CharacterOriginUI(
            name = this.origin.name,
            url = this.origin.url,
        ),
        location = CharacterLocationUI(
            name = this.location.name,
            url = this.location.url,
        ),
        image = this.image,
        episode = this.episode,
        url = this.url,
    )
}


internal fun Location.toLocationUI() : LocationUI {
    return LocationUI(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension,
        residents = this.residents,
        url = this.url,
    )
}
