package com.evg.ram_api.di

import com.evg.ram_api.KtorClient
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
        println("provided provideKtorClient")
        return KtorClient()
    }
}