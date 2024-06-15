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
import com.evg.characters.domain.mapper.toCharacterFilterDB
import com.evg.characters.domain.mapper.toCharacterFilterDTO
import com.evg.characters.domain.mapper.toEpisode
import com.evg.characters.domain.model.CharacterFilter
import com.evg.characters.domain.model.Episode
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.ram_api.data.CharacterPageSourceRemote
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val context: Context,
    private val apiRepository: ApiRepository,
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
            )) { characterPageSourceLocal.apply { this.filter = filter.toCharacterFilterDB() } }.flow.map { pagingData ->
                pagingData.map {
                    it.toCharacter()
                }
            }
        }
    }

    override fun getCharacterById(id: Int): Flow<Character?> {
        return flow {
            when (val response = apiRepository.getCharacterById(id = id)) {
                is Response.Success -> {
                    emit(response.data.toCharacter())
                }
                is Response.Failure -> {
                    emit(null)
                }
            }
        }
    }

    override fun getEpisodesList(urlsList: List<String>): Flow<List<Episode>?> {
        val listIds = urlsList.mapNotNull { url ->
            val matchResult = Regex("""/episode/(\d+)""").find(url)
            matchResult?.groups?.get(1)?.value?.toIntOrNull()
        }

        return flow {
            when (val response = apiRepository.getEpisodesList(listIds = listIds)) {
                is Response.Success -> {
                    emit(response.data.map { it.toEpisode() } )
                }
                is Response.Failure -> {
                    emit(null)
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