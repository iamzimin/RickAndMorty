package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evg.database.domain.converter.EpisodeConverter
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.LocationDBO

@Database(
    entities = [LocationDBO::class],
    version = 1
)
@TypeConverters(EpisodeConverter::class)
abstract class LocationDatabase: RoomDatabase() {
    abstract val locationDao: LocationDao

    companion object {
        const val DATABASE_NAME = "location"
    }
}