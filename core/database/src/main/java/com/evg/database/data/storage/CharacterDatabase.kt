package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evg.database.domain.converter.CharacterConverter
import com.evg.database.domain.models.CharacterDBO

@Database(
    entities = [CharacterDBO::class],
    version = 1
)
@TypeConverters(CharacterConverter::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract val characterDao: CharactersDao

    companion object {
        const val DATABASE_NAME = "characters"
    }
}