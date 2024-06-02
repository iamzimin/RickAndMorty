package com.evg.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.evg.characters.domain.usecase.CharactersUseCases
import com.evg.ram_api.KtorClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    val charactersUseCases: KtorClient,
) : ViewModel() {

}