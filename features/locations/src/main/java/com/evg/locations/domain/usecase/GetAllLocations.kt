package com.evg.locations.domain.usecase

import androidx.paging.PagingData
import com.evg.locations.domain.model.Location
import com.evg.locations.domain.model.LocationFilter
import com.evg.locations.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocations @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun invoke(filter: LocationFilter): Flow<PagingData<Location>> {
        return locationRepository.getAllLocations(filter = filter)
    }
}