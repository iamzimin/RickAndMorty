package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.EpisodeDBO
import com.evg.database.domain.models.LocationDBO

@Dao
interface LocationDao {
    @Query(
        """SELECT * FROM locationdbo
            WHERE
                (:name IS NULL OR name LIKE '%' || :name || '%')
            LIMIT :pageSize OFFSET :offset;"""
    )
    fun getAllLocationsByPage(
        pageSize: Int,
        offset: Int,
        name: String?,
    ): List<LocationDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationDBO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationDBO>)

    @Query("SELECT * FROM locationdbo WHERE id = :id")
    suspend fun getLocationById(id: Int): LocationDBO?
}