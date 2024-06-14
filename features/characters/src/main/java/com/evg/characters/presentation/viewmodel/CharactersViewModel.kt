package com.evg.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.model.CharacterFilter
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
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharactersViewModel @Inject constructor(
    charactersUseCases: CharactersUseCases,
) : ViewModel() {
    private val filter = MutableStateFlow(CharacterFilter())

    val characters: StateFlow<PagingData<Character>> = filter.flatMapLatest { currentFilter ->
        charactersUseCases.getAllCharacters.invoke(filter = currentFilter)
    }.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

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