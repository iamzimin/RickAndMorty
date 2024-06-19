package com.evg.database.domain.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LocationConvert {
    @TypeConverter
    fun fromResidentsList(residentsList: List<String>): String {
        return Json.encodeToString(residentsList)
    }

    @TypeConverter
    fun toResidentsList(residentsListString: String): List<String> {
        return Json.decodeFromString(residentsListString)
    }
}