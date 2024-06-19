package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evg.database.domain.converter.CharacterConverter
import com.evg.database.domain.converter.EpisodeConverter
import com.evg.database.domain.models.CharacterDBO
import com.evg.database.domain.models.EpisodeDBO

@Database(
    entities = [EpisodeDBO::class],
    version = 1
)
@TypeConverters(EpisodeConverter::class)
abstract class EpisodeDatabase: RoomDatabase() {
    abstract val episodeDao: EpisodesDao

    companion object {
        const val DATABASE_NAME = "episode"
    }
}