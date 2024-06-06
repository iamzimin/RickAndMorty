package com.evg.database.domain.converter

import androidx.room.TypeConverter
import com.evg.database.domain.models.CharacterLocationDBO
import com.evg.database.domain.models.CharacterOriginDBO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CharacterConverter {
    @TypeConverter
    fun fromOriginResponse(origin: CharacterOriginDBO): String {
        return Json.encodeToString(origin)
    }

    @TypeConverter
    fun toOriginResponse(originString: String): CharacterOriginDBO {
        return Json.decodeFromString(originString)
    }

    @TypeConverter
    fun fromLocationResponse(location: CharacterLocationDBO): String {
        return Json.encodeToString(location)
    }

    @TypeConverter
    fun toLocationResponse(locationString: String): CharacterLocationDBO {
        return Json.decodeFromString(locationString)
    }

    @TypeConverter
    fun fromEpisodeList(episodeList: List<String>): String {
        return Json.encodeToString(episodeList)
    }

    @TypeConverter
    fun toEpisodeList(episodeListString: String): List<String> {
        return Json.decodeFromString(episodeListString)
    }
}