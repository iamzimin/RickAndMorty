package com.evg.characters.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.evg.resource.theme.RickAndMortyTheme


@Composable
fun CharactersScreen() {
    Column {
        FragmentHeader()
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    RickAndMortyTheme {
        CharactersScreen()
    }
}