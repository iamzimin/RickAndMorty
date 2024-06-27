package com.evg.characters.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.characters.presentation.mapper.toCharacterUI
import com.evg.characters.presentation.mapper.toEpisodeUI
import com.evg.characters.presentation.viewmodel.CharactersViewModel
import com.evg.resource.CustomSwipeRefreshIndicator
import com.evg.resource.NotFound
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.LazyColumnNoInfoPadding
import com.evg.resource.theme.RickAndMortyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun CharacterScreen(
    characterId: Int,
    viewModel: CharactersViewModel = hiltViewModel<CharactersViewModel>(),
) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val characterInfo by viewModel.characterInfo.collectAsState()
    val characterEpisodes by viewModel.characterEpisodes.collectAsState()
    val isLoading by viewModel.isInfoLoading.collectAsState()
    val isEpisodesLoading by viewModel.isEpisodesLoading.collectAsState()

    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)

    if (!isInitialized) {
        LaunchedEffect(characterId) {
            viewModel.getCharacterInfo(characterId)
            isInitialized = true
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.DarkGray)
            .padding(horizontal = EdgesMargin),
    ) {
        SwipeRefresh(
            state = refreshingState,
            onRefresh = { viewModel.getCharacterInfo(characterId) },
            indicator = { state, trigger ->
                CustomSwipeRefreshIndicator(
                    state = state,
                    trigger = trigger,
                )
            },
        ) {
            if (isLoading) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                if (characterInfo == null) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = LazyColumnNoInfoPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        NotFound(
                            imageSize = 200,
                            textStyle = MaterialTheme.typography.titleLarge,
                            pageName = "Character",
                        )
                    }
                } else {
                    characterInfo?.let { info ->
                        CharacterInfo(
                            characterUI = info.toCharacterUI(),
                            episodesUI = characterEpisodes?.map { it.toEpisodeUI() },
                            isEpisodesLoading = isEpisodesLoading,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharacterCardPreview() {
    RickAndMortyTheme {
        CharacterScreen(characterId = 1)
    }
}

