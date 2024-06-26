package com.evg.locations.domain.usecase

import com.evg.locations.domain.model.Character
import com.evg.locations.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersList @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun invoke(urlsList: List<String>): Flow<List<Character>?> {
        return locationRepository.getCharactersList(urlsList = urlsList)
    }
}