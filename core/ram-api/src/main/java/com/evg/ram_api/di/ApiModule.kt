package com.evg.ram_api.di

import android.content.Context
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.ram_api.data.CharacterPageSourceRemote
import com.evg.ram_api.domain.KtorClient
import com.evg.ram_api.data.repository.ApiRepositoryImpl
import com.evg.ram_api.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideApiRepository(
        @ApplicationContext context: Context,
        ktorClient: KtorClient,
        databaseRepository: DatabaseRepository
    ): ApiRepository {
        println("provided ApiRepositoryImpl")
        return ApiRepositoryImpl(
            context = context,
            ktor = ktorClient,
            databaseRepository = databaseRepository,
        )
    }

    @Provides
    @Singleton
    fun provideCharacterPageSourceRemote(apiRepository: ApiRepository): CharacterPageSourceRemote {
        println("provided provideCharacterPageSourceRemote")
        return CharacterPageSourceRemote(apiRepository = apiRepository)
    }
}