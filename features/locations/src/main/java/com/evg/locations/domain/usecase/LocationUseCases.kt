package com.evg.locations.domain.usecase

import javax.inject.Inject

class LocationUseCases @Inject constructor(
    val getAllLocations: GetAllLocations,
    val getLocationById: GetLocationById,
    val getCharactersList: GetCharactersList,
)