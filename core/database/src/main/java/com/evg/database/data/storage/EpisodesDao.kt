package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.EpisodeDBO

@Dao
interface EpisodesDao {
    @Query(
        """SELECT * FROM episodedbo
            WHERE
                (:name IS NULL OR name LIKE '%' || :name || '%')
            LIMIT :pageSize OFFSET :offset;"""
    )
    fun getAllEpisodesByPage(
        pageSize: Int,
        offset: Int,
        name: String?,
    ): List<EpisodeDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeDBO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeDBO>)
}