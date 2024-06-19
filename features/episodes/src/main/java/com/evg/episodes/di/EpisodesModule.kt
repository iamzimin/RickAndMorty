package com.evg.episodes.di

import android.content.Context
import com.evg.database.data.EpisodePageSourceLocal
import com.evg.episodes.data.repository.EpisodesRepositoryImpl
import com.evg.episodes.domain.repository.EpisodesRepository
import com.evg.ram_api.data.EpisodePageSourceRemote
import com.evg.ram_api.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodesModule {
    @Provides
    @Singleton
    fun provideEpisodeRepository(
        @ApplicationContext context: Context,
        apiRepository: ApiRepository,
        episodesPageSourceLocal: EpisodePageSourceLocal,
        episodesPageSourceRemote: EpisodePageSourceRemote,
    ): EpisodesRepository {
        println("provided CharactersRepositoryImpl")
        return EpisodesRepositoryImpl(
            apiRepository = apiRepository,
            episodesPageSourceLocal = episodesPageSourceLocal,
            episodesPageSourceRemote = episodesPageSourceRemote,
        )
    }
}