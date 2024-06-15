package com.evg.characters.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterFilter
import com.evg.characters.domain.model.Episode
import com.evg.characters.domain.model.GenderType
import com.evg.characters.domain.model.StatusType
import com.evg.characters.domain.usecase.CharactersUseCases
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
class CharactersViewModel @Inject constructor(
    private val charactersUseCases: CharactersUseCases,
) : ViewModel() {
    private val filter = MutableStateFlow(CharacterFilter())
    val characters: StateFlow<PagingData<Character>> = filter.flatMapLatest { currentFilter ->
        charactersUseCases.getAllCharacters.invoke(filter = currentFilter)
    }.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private val _characterInfo = MutableStateFlow<Character?>(null)
    val characterInfo: StateFlow<Character?> get() = _characterInfo

    private val _characterEpisodes = MutableStateFlow<List<Episode>?>(null)
    val characterEpisodes: StateFlow<List<Episode>?> get() = _characterEpisodes

    var selectedStatus by mutableStateOf<StatusType?>(null)
    var selectedSpecies by mutableStateOf<String?>(null)
    var selectedGender by mutableStateOf<GenderType?>(null)

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            charactersUseCases.getCharacterById(id = id)
                .collect { character ->
                    _characterInfo.value = character
                    character?.let {
                        getCharacterEpisodes(it.episode)
                    }
                }
        }
    }

    private fun getCharacterEpisodes(episodeUrls: List<String>) {
        viewModelScope.launch {
            charactersUseCases.getEpisodesList(episodeUrls)
                .collect { episodes ->
                    _characterEpisodes.value = episodes
                }
        }
    }

    fun setNameFilter(name: String) {
        filter.value = filter.value.copy(name = name)
    }

    fun setCharacterStatus(status: StatusType?) {
        filter.value = filter.value.copy(status = status)
    }

    fun setCharacterSpecies(species: String?) {
        filter.value = filter.value.copy(species = species)
    }

    fun setCharacterGender(gender: GenderType?) {
        filter.value = filter.value.copy(gender = gender)
    }
}