package com.evg.characters.di

import com.evg.characters.data.repository.CharactersRepositoryImpl
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.ram_api.data.CharacterPageSourceRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {
    @Provides
    @Singleton
    fun provideCharactersRepository(
        characterPageSourceLocal: CharacterPageSourceLocal,
        characterPageSourceRemote: CharacterPageSourceRemote,
    ): CharactersRepository {
        println("provided CharactersRepositoryImpl")
        return CharactersRepositoryImpl(
            characterPageSourceLocal = characterPageSourceLocal,
            characterPageSourceRemote = characterPageSourceRemote,
        )
    }
}