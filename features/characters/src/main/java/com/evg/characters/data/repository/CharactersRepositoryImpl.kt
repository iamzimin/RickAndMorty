package com.evg.characters.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.domain.mapper.toCharacter
import com.evg.characters.domain.mapper.toCharacterFilterDTO
import com.evg.characters.domain.model.CharacterFilter
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.ram_api.data.CharacterPageSourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val context: Context,
    private val characterPageSourceLocal: CharacterPageSourceLocal,
    private val characterPageSourceRemote: CharacterPageSourceRemote,
): CharactersRepository {
    override fun getAllCharacters(filter: CharacterFilter): Flow<PagingData<Character>> {
        if (isInternetAvailable()) {
            return Pager(PagingConfig(
                pageSize = 5,
            )) { characterPageSourceRemote.apply { this.filter = filter.toCharacterFilterDTO() } }.flow.map { pagingData ->
                pagingData.map {
                    it.toCharacter()
                }
            }
        } else {
            return Pager(PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
            )) { characterPageSourceLocal }.flow.map { pagingData ->
                pagingData.map {
                    it.toCharacter()
                }
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}