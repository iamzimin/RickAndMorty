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

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCases: CharactersUseCases,
) : ViewModel() {
    private val filter = MutableStateFlow(CharacterFilter())

    private val _characters = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    val characters: StateFlow<PagingData<Character>> get() = _characters

    private val _characterInfo = MutableStateFlow<Character?>(null)
    val characterInfo: StateFlow<Character?> get() = _characterInfo

    private val _characterEpisodes = MutableStateFlow<List<Episode>?>(null)
    val characterEpisodes: StateFlow<List<Episode>?> get() = _characterEpisodes

    private val _isInfoLoading = MutableStateFlow(true)
    val isInfoLoading: StateFlow<Boolean> = _isInfoLoading

    private val _isEpisodesLoading = MutableStateFlow(true)
    val isEpisodesLoading: StateFlow<Boolean> = _isEpisodesLoading

    var selectedStatus by mutableStateOf<StatusType?>(null)
    var selectedSpecies by mutableStateOf<String?>(null)
    var selectedGender by mutableStateOf<GenderType?>(null)

    init {
        updateCharacters()
    }

    fun updateCharacters() {
        viewModelScope.launch {
            charactersUseCases.getAllCharacters.invoke(filter = filter.value)
                .cachedIn(viewModelScope)
                .collect { characters ->
                    _characters.value = characters
                }
        }
    }

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            _isInfoLoading.value = true
            charactersUseCases.getCharacterById.invoke(id = id)
                .collect { character ->
                    _characterInfo.value = character
                    character?.let {
                        getCharacterEpisodes(it.episode)
                    }
                    _isInfoLoading.value = false
                }
        }
    }

    private fun getCharacterEpisodes(episodeUrls: List<String>) {
        viewModelScope.launch {
            _isEpisodesLoading.value = true
            charactersUseCases.getEpisodesList.invoke(episodeUrls)
                .collect { episodes ->
                    _characterEpisodes.value = episodes
                    _isEpisodesLoading.value = false
                }
        }
    }

    fun setNameFilter(name: String) {
        filter.value = filter.value.copy(name = name)
        updateCharacters()
    }

    fun setCharacterStatus(status: StatusType?) {
        filter.value = filter.value.copy(status = status)
        selectedStatus = filter.value.status
        updateCharacters()
    }

    fun setCharacterSpecies(species: String?) {
        filter.value = filter.value.copy(species = species)
        selectedSpecies = filter.value.species
        updateCharacters()
    }

    fun setCharacterGender(gender: GenderType?) {
        filter.value = filter.value.copy(gender = gender)
        selectedGender = filter.value.gender
        updateCharacters()
    }
}