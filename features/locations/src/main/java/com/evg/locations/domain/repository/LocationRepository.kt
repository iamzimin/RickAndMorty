package com.evg.locations.domain.repository

import com.evg.locations.domain.model.Character
import androidx.paging.PagingData
import com.evg.locations.domain.model.Location
import com.evg.locations.domain.model.LocationFilter
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAllLocations(filter: LocationFilter): Flow<PagingData<Location>>
    fun getLocationById(id: Int): Flow<Location?>
    fun getCharactersList(urlsList: List<String>): Flow<List<Character>?>
}