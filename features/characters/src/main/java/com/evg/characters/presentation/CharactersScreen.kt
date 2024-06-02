package com.evg.characters.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evg.characters.domain.usecase.CharactersUseCases
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.characters.presentation.viewmodel.CharactersViewModelFactory
import com.evg.resource.theme.RickAndMortyTheme
import javax.inject.Inject


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>(),
) {

    Column {
        FragmentHeader()
        LazyColumn {

        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    RickAndMortyTheme {
        CharactersScreen()
    }
}