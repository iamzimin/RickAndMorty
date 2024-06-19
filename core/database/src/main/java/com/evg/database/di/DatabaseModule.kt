package com.evg.database.di

import android.content.Context
import androidx.room.Room
import com.evg.database.data.CharacterPageSourceLocal
import com.evg.database.data.repository.DatabaseRepositoryImpl
import com.evg.database.data.storage.CharacterDatabase
import com.evg.database.data.storage.EpisodeDatabase
import com.evg.database.data.storage.LocationDatabase
import com.evg.database.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context) : CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            CharacterDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideEpisodeDatabase(@ApplicationContext context: Context) : EpisodeDatabase {
        return Room.databaseBuilder(
            context,
            EpisodeDatabase::class.java,
            EpisodeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocationDatabase(@ApplicationContext context: Context) : LocationDatabase {
        return Room.databaseBuilder(
            context,
            LocationDatabase::class.java,
            LocationDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        characterDatabase: CharacterDatabase,
        episodeDatabase: EpisodeDatabase,
        locationDatabase: LocationDatabase,
    ): DatabaseRepository {
        println("provided DatabaseRepositoryImpl")
        return DatabaseRepositoryImpl(
            characterDatabase = characterDatabase,
            episodeDatabase = episodeDatabase,
            locationDatabase = locationDatabase,
        )
    }

    @Provides
    @Singleton
    fun provideCharacterPageSourceLocal(databaseRepository: DatabaseRepository): CharacterPageSourceLocal {
        println("provided CharacterPageSourceLocal")
        return CharacterPageSourceLocal(databaseRepository = databaseRepository)
    }
}