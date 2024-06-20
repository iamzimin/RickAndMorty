package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.CharacterDBO

@Dao
interface CharactersDao {
    @Query(
        """SELECT * FROM characterdbo
            WHERE
                (:name IS NULL OR name LIKE '%' || :name || '%') AND
                (:status IS NULL OR status LIKE '%' || :status || '%') AND
                (:species IS NULL OR species LIKE '%' || :species || '%') AND
                (:type IS NULL OR type LIKE '%' || :type || '%') AND
                (:gender IS NULL OR gender = :gender) 
            LIMIT :pageSize OFFSET :offset;"""
    )
    fun getAllCharactersByPage(
        pageSize: Int,
        offset: Int,
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): List<CharacterDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDBO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterDBO>)

    @Query("SELECT * FROM characterdbo WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterDBO?
}