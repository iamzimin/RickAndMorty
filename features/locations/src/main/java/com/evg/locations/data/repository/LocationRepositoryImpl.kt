package com.evg.locations.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.evg.database.data.LocationPageSourceLocal
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.locations.domain.mapper.toCharacter
import com.evg.locations.domain.mapper.toLocation
import com.evg.locations.domain.mapper.toLocationFilterDB
import com.evg.locations.domain.mapper.toLocationFilterDTO
import com.evg.locations.domain.model.Character
import com.evg.locations.domain.model.Location
import com.evg.locations.domain.model.LocationFilter
import com.evg.locations.domain.repository.LocationRepository
import com.evg.ram_api.data.LocationPageSourceRemote
import com.evg.ram_api.domain.Response
import com.evg.ram_api.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiRepository: ApiRepository,
    private val databaseRepository: DatabaseRepository,
    private val locationPageSourceLocal: LocationPageSourceLocal,
    private val locationPageSourceRemote: LocationPageSourceRemote,
): LocationRepository {
    override fun getAllLocations(filter: LocationFilter): Flow<PagingData<Location>> {
        if (apiRepository.isInternetAvailable()) {
            return Pager(
                PagingConfig(
                    pageSize = 5,
                )
            ) { locationPageSourceRemote.apply { this.filter = filter.toLocationFilterDTO() } }.flow.map { pagingData ->
                pagingData.map {
                    it.toLocation()
                }
            }
        } else {
            return Pager(
                PagingConfig(
                    pageSize = 10,
                    initialLoadSize = 10,
                )
            ) { locationPageSourceLocal.apply { this.filter = filter.toLocationFilterDB() } }.flow.map { pagingData ->
                pagingData.map {
                    it.toLocation()
                }
            }
        }
    }

    override fun getLocationById(id: Int): Flow<Location?> {
        return flow {
            when (val response = apiRepository.getLocationById(id = id)) {
                is Response.Success -> {
                    emit(response.data.toLocation())
                }
                is Response.Failure -> {
                    val data = databaseRepository.getLocationById(id = id)?.toLocation()
                    emit(data)
                }
            }
        }
    }

    override fun getCharactersList(urlsList: List<String>): Flow<List<Character>?> {
        val listIds = urlsList.mapNotNull { url ->
            val matchResult = Regex("""/character/(\d+)""").find(url)
            matchResult?.groups?.get(1)?.value?.toIntOrNull()
        }

        return flow {
            when (val response = apiRepository.getCharactersList(listIds = listIds)) {
                is Response.Success -> {
                    emit(response.data.map { it.toCharacter() } )
                }
                is Response.Failure -> {
                    emit(null)
                }
            }
        }
    }
}