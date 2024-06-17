package com.evg.resource.model.character

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.evg.resource.R
import com.evg.resource.theme.UnknownBlack
import com.evg.resource.theme.AliveGreen
import com.evg.resource.theme.DeadRed

data class CharacterUI(
    val id: Int,
    val name: String,
    val status: CharacterStatusUI,
    val species: String,
    val type: String,
    val gender: CharacterGenderUI,
    val origin: CharacterOriginUI,
    val location: CharacterLocationUI,
    val image: String,
    val episode: List<String>,
    val url: String,
)

enum class CharacterStatusUI(
    val color: Color,
    @StringRes
    val naming: Int
) {
    ALIVE(
        AliveGreen,
        R.string.alive
    ),
    DEAD(
        DeadRed,
        R.string.dead
    ),
    UNKNOWN(
        UnknownBlack,
        R.string.unknown
    )
}

enum class CharacterGenderUI(
    @StringRes
    val gender: Int,
) {
    FEMALE(
        R.string.female
    ),
    MALE(
        R.string.male
    ),
    GENDERLESS(
        R.string.genderless
    ),
    UNKNOWN(
        R.string.unknown
    )
}

data class CharacterOriginUI(
    val name: String,
    val url: String,
)

data class CharacterLocationUI(
    val name: String,
    val url: String,
)