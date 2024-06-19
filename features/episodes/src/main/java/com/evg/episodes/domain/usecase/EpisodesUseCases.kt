package com.evg.episodes.domain.usecase

import javax.inject.Inject

data class EpisodesUseCases @Inject constructor(
    val getAllEpisodes: GetAllEpisodes,
    val getEpisodeById: GetEpisodeById,
    val getCharactersList: GetCharactersList,
)
