package com.evg.characters.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.characters.presentation.mapper.toCharacterUI
import com.evg.characters.presentation.mapper.toEpisodeUI
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.theme.RickAndMortyTheme


@Composable
fun CharacterScreen(
    characterId: Int,
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>(),
) {
    val context = LocalContext.current

    val characterInfo by viewModel.characterInfo.collectAsState()
    val characterEpisodes by viewModel.characterEpisodes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.getCharacterInfo(characterId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.DarkGray)
            .padding(horizontal = 20.dp, vertical = 40.dp),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.Red
            )
        } else {
            characterInfo?.let { info ->
                characterEpisodes?.let { episodes ->
                    CharacterInfo(
                        characterUI = info.toCharacterUI(),
                        episodesUI = episodes.map { it.toEpisodeUI() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    RickAndMortyTheme {
        CharacterScreen(characterId = 1)
    }
}

