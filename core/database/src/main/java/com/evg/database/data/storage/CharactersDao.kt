package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.CharacterDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("SELECT * FROM characterdbo LIMIT :pageSize OFFSET :offset")
    fun getAllCharactersByPage(pageSize: Int, offset: Int): List<CharacterDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterDBO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterDBO>)
}