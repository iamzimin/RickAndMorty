package com.evg.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.domain.mapper.toCharacter
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.ram_api.data.CharacterPageSourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterPageSourceLocal: CharacterPageSourceLocal,
    private val characterPageSourceRemote: CharacterPageSourceRemote,
): CharactersRepository { // TODO if PagingData Error use CharacterPageSourceLocal
    override fun getAllCharacters(): Flow<PagingData<Character>> {
        val remotePager = Pager(PagingConfig(pageSize = 5)) { characterPageSourceRemote }.flow.map { pagingData ->
            pagingData.map {
                it.toCharacter()
            }
        }
        val localPager = Pager(PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
        )) { characterPageSourceLocal }.flow.map { pagingData ->
            pagingData.map {
                it.toCharacter()
            }
        }
        return remotePager
    }
}