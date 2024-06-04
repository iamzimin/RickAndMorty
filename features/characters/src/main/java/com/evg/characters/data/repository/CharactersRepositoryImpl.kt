package com.evg.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.domain.toCharacter
import com.evg.ram_api.data.CharacterPageSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterPageSource: CharacterPageSource
): CharactersRepository {
    override fun getAllCharacters(): Flow<PagingData<Character>> {
        /*return dataSource.getAllCharactersByPage().map { elem ->
            elem.map {
                it.toCharacter()
            }
        }*/
        return Pager(PagingConfig(pageSize = 5)) { characterPageSource }.flow.map { pagingData ->
            pagingData.map {
                it.toCharacter()
            }
        }
    }
}