package com.evg.ram_api.di

import com.evg.ram_api.data.CharacterPageSource
import com.evg.ram_api.domain.KtorClient
import com.evg.ram_api.data.repository.ApiRepositoryImpl
import com.evg.ram_api.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideKtorClient(): KtorClient {
        println("provided KtorClient")
        return KtorClient()
    }

    @Provides
    @Singleton
    fun provideApiRepository(ktorClient: KtorClient): ApiRepository {
        println("provided ApiRepositoryImpl")
        return ApiRepositoryImpl(ktor = ktorClient)
    }

    @Provides
    @Singleton
    fun provideCharacterPageSource(apiRepository: ApiRepository): CharacterPageSource {
        println("provided CharacterPageSource")
        return CharacterPageSource(apiRepository = apiRepository)
    }
}