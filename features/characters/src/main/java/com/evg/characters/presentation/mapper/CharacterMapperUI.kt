package com.evg.characters.presentation.mapper

import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterGender
import com.evg.characters.domain.model.CharacterStatus
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI

internal fun Character.toCharacterUI() : CharacterUI {
    return CharacterUI(
        id = this.id,
        name = this.name,
        status = when (this.status) {
            CharacterStatus.ALIVE -> CharacterStatusUI.ALIVE
            CharacterStatus.DEAD -> CharacterStatusUI.DEAD
            else -> CharacterStatusUI.UNKNOWN
        },
        species = this.species,
        type = this.type,
        gender = when (this.gender) {
            CharacterGender.FEMALE -> CharacterGenderUI.FEMALE
            CharacterGender.MALE -> CharacterGenderUI.MALE
            CharacterGender.GENDERLESS -> CharacterGenderUI.GENDERLESS
            else -> CharacterGenderUI.UNKNOWN
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