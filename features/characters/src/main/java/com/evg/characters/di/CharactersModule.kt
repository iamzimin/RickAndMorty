package com.evg.characters.di

import com.evg.characters.data.repository.CharactersRepositoryImpl
import com.evg.characters.domain.repository.CharactersRepository
import com.evg.characters.domain.usecase.CharactersUseCases
import com.evg.ram_api.data.CharacterPageSource
import com.evg.ram_api.domain.repository.ApiRepository
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
    fun provideCharactersRepository(characterPageSource: CharacterPageSource): CharactersRepository {
        println("provided CharactersRepositoryImpl")
        return CharactersRepositoryImpl(characterPageSource = characterPageSource)
    }
}