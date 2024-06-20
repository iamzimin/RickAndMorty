package com.evg.locations.di

import android.content.Context
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.database.data.LocationPageSourceLocal
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.locations.data.repository.LocationRepositoryImpl
import com.evg.locations.domain.repository.LocationRepository
import com.evg.ram_api.data.CharacterPageSourceRemote
import com.evg.ram_api.data.LocationPageSourceRemote
import com.evg.ram_api.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationsModule {
    @Provides
    @Singleton
    fun provideLocationRepository(
        apiRepository: ApiRepository,
        databaseRepository: DatabaseRepository,
        locationPageSourceLocal: LocationPageSourceLocal,
        locationPageSourceRemote: LocationPageSourceRemote,
    ): LocationRepository {
        println("provided CharactersRepositoryImpl")
        return LocationRepositoryImpl(
            apiRepository = apiRepository,
            databaseRepository = databaseRepository,
            locationPageSourceLocal = locationPageSourceLocal,
            locationPageSourceRemote = locationPageSourceRemote,
        )
    }
}