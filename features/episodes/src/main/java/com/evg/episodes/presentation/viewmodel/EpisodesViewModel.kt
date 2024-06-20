package com.evg.episodes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.evg.episodes.domain.model.Character
import com.evg.episodes.domain.model.Episode
import com.evg.episodes.domain.model.EpisodeFilter
import com.evg.episodes.domain.usecase.EpisodesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesUseCases: EpisodesUseCases,
): ViewModel() {
    private val filter = MutableStateFlow(EpisodeFilter())
    val episodes: StateFlow<PagingData<Episode>> = filter.flatMapLatest { currentFilter ->
        episodesUseCases.getAllEpisodes.invoke(filter = currentFilter)
    }.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private val _episodeInfo = MutableStateFlow<Episode?>(null)
    val episodeInfo: StateFlow<Episode?> get() = _episodeInfo

    private val _episodeCharacters = MutableStateFlow<List<Character>?>(null)
    val episodeCharacters: StateFlow<List<Character>?> get() = _episodeCharacters

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getEpisodeInfo(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            episodesUseCases.getEpisodeById(id = id)
                .collect { episode ->
                    _episodeInfo.value = episode
                    episode?.let {
                        getEpisodeCharacters(it.characters)
                    }
                    _isLoading.value = false
                }
        }
    }

    private fun getEpisodeCharacters(episodeUrls: List<String>) {
        viewModelScope.launch {
            episodesUseCases.getCharactersList(episodeUrls)
                .collect { characters ->
                    _episodeCharacters.value = characters
                }
        }
    }

    fun setNameFilter(name: String) {
        filter.value = filter.value.copy(name = name)
    }
}