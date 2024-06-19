package com.evg.database.domain.converter

import androidx.room.TypeConverter
import com.evg.database.domain.models.CharacterLocationDBO
import com.evg.database.domain.models.CharacterOriginDBO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object EpisodeConverter {
    @TypeConverter
    fun fromCharacterList(characterList: List<String>): String {
        return Json.encodeToString(characterList)
    }

    @TypeConverter
    fun toCharacterList(characterListString: String): List<String> {
        return Json.decodeFromString(characterListString)
    }
}