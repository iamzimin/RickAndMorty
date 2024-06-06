package com.evg.characters.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.evg.characters.presentation.mapper.toCharacterUI
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.theme.RickAndMortyTheme


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>(),
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    Column {
        FragmentHeader()
        LazyColumn {
            items(
                count = characters.itemCount,
                key = characters.itemKey { it.id },
                contentType = characters.itemContentType { it.image }
            ) { index ->
                val item = characters[index]
                if (item != null) {
                    CharacterCard(
                        characterUI = item.toCharacterUI()
                    )
                }
            }
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