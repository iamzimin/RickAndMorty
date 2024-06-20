package com.evg.characters.di

import android.content.Context
import com.evg.characters.data.repository.CharactersRepositoryImpl
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.ram_api.data.CharacterPageSourceRemote
import com.evg.ram_api.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {
    @Provides
    @Singleton
    fun provideCharacterRepository(
        apiRepository: ApiRepository,
        databaseRepository: DatabaseRepository,
        characterPageSourceLocal: CharacterPageSourceLocal,
        characterPageSourceRemote: CharacterPageSourceRemote,
    ): CharactersRepository {
        println("provided CharactersRepositoryImpl")
        return CharactersRepositoryImpl(
            apiRepository = apiRepository,
            databaseRepository = databaseRepository,
            characterPageSourceLocal = characterPageSourceLocal,
            characterPageSourceRemote = characterPageSourceRemote,
        )
    }
}