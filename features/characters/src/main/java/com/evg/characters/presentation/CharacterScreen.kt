package com.evg.characters.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.theme.RickAndMortyTheme


@Composable
fun CharacterScreen(
    characterId: Int,
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>()
) {
    val characterInfo by viewModel.characterInfo.collectAsState()
    val characterEpisodes by viewModel.characterEpisodes.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.getCharacterInfo(characterId)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Character ${characterInfo?.name}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Episodes ${characterEpisodes?.map { "${it.name} \n" }}",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }

    }
    Column {

    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    RickAndMortyTheme {
        CharacterScreen(characterId = 1)
    }
}