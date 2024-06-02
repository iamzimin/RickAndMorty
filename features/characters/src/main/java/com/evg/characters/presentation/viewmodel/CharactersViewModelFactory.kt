package com.evg.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evg.characters.domain.usecase.CharactersUseCases
import com.evg.ram_api.KtorClient

class CharactersViewModelFactory(
    val charactersUseCases: KtorClient,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            charactersUseCases = charactersUseCases,
        ) as T
    }
}