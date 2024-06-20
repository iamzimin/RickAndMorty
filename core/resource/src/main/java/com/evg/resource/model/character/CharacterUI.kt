package com.evg.resource.model.character

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.evg.resource.R
import com.evg.resource.theme.AliveGreenDark
import com.evg.resource.theme.AliveGreenLight
import com.evg.resource.theme.DeadRedDark
import com.evg.resource.theme.DeadRedLight
import com.evg.resource.theme.UnknownBlackDark
import com.evg.resource.theme.UnknownBlackLight

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
    @StringRes
    val naming: Int
) {
    ALIVE(
        R.string.alive
    ),
    DEAD(
        R.string.dead
    ),
    UNKNOWN(
        R.string.unknown
    )
}

@Composable
fun CharacterStatusUI.color(): Color {
    return when (this) {
        CharacterStatusUI.ALIVE -> if (isSystemInDarkTheme()) AliveGreenDark else AliveGreenLight
        CharacterStatusUI.DEAD -> if (isSystemInDarkTheme()) DeadRedDark else DeadRedLight
        CharacterStatusUI.UNKNOWN -> if (isSystemInDarkTheme()) UnknownBlackDark else UnknownBlackLight
    }
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