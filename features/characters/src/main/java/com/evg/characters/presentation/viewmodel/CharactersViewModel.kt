package com.evg.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.evg.characters.domain.model.Character
import com.evg.characters.domain.usecase.CharactersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    charactersUseCases: CharactersUseCases,
) : ViewModel() {
    val characters = charactersUseCases.getAllCharacters.invoke().asLiveData(viewModelScope.coroutineContext)

    init {
        characters.observeForever {
            it.forEach{ ch ->
                println(ch.name)
            }
        }
    }
}