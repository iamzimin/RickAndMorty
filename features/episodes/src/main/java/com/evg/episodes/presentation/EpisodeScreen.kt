package com.evg.episodes.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.episodes.presentation.mapper.toCharacterUI
import com.evg.episodes.presentation.mapper.toEpisodeUI
import com.evg.episodes.presentation.viewmodel.EpisodesViewModel
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun EpisodeScreen(
    episodeId: Int,
    viewModel: EpisodesViewModel = hiltViewModel<EpisodesViewModel>(),
) {
    val episodeInfo by viewModel.episodeInfo.collectAsState()
    val episodeCharacters by viewModel.episodeCharacters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(episodeId) {
        viewModel.getEpisodeInfo(episodeId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.DarkGray)
            .padding(horizontal = EdgesMargin),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            episodeInfo?.let { info ->
                episodeCharacters?.let { episodes ->
                    EpisodeInfo(
                        episodeUI = info.toEpisodeUI(),
                        charactersUI = episodes.map { it.toCharacterUI() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EpisodesScreenPreview() {
    RickAndMortyTheme {
        EpisodeScreen(episodeId = 1)
    }
}
