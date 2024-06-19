package com.evg.locations.domain.usecase

import com.evg.locations.domain.model.Location
import com.evg.locations.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationById @Inject constructor(
    private val locationRepository: LocationRepository
) {
    operator fun invoke(id: Int): Flow<Location?> {
        return locationRepository.getLocationById(id = id)
    }
}