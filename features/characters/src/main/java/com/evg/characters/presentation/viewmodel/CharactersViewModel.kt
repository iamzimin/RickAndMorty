package com.evg.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.usecase.CharactersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    charactersUseCases: CharactersUseCases,
) : ViewModel() {
    val characters: StateFlow<PagingData<Character>> =
        charactersUseCases.getAllCharacters.invoke().stateIn(
            viewModelScope, SharingStarted.Lazily, PagingData.empty()
        )
}