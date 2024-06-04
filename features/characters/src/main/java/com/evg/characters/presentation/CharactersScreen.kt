package com.evg.characters.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.characters.domain.model.Character
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.theme.RickAndMortyTheme


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>(),
) {
    var characters by remember {
        mutableStateOf<List<Character>?>(null)
    }
    
    LaunchedEffect(key1 = Unit, block = {
        characters = viewModel.characters.value
    })

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