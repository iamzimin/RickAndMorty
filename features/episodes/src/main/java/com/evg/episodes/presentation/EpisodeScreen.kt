package com.evg.episodes.presentation

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
import com.evg.episodes.presentation.mapper.toCharacterUI
import com.evg.episodes.presentation.mapper.toEpisodeUI
import com.evg.episodes.presentation.viewmodel.EpisodesViewModel
import com.evg.resource.CustomSwipeRefreshIndicator
import com.evg.resource.NoInternetConnection
import com.evg.resource.NotFound
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.LazyColumnNoInfoPadding
import com.evg.resource.theme.RickAndMortyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun EpisodeScreen(
    episodeId: Int,
    viewModel: EpisodesViewModel = hiltViewModel<EpisodesViewModel>(),
) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val episodeInfo by viewModel.episodeInfo.collectAsState()
    val episodeCharacters by viewModel.episodeCharacters.collectAsState()
    val isLoading by viewModel.isInfoLoading.collectAsState()
    val isCharactersLoading by viewModel.isCharactersLoading.collectAsState()

    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)

    if (!isInitialized) {
        LaunchedEffect(episodeId) {
            viewModel.getEpisodeInfo(episodeId)
            isInitialized = true
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = EdgesMargin),
    ) {
        SwipeRefresh(
            state = refreshingState,
            onRefresh = { viewModel.getEpisodeInfo(episodeId) },
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
                if (episodeInfo == null) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = LazyColumnNoInfoPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        NotFound(
                            imageSize = 200,
                            textStyle = MaterialTheme.typography.titleLarge,
                            pageName = "Episode",
                        )
                    }
                } else {
                    episodeInfo?.let { info ->
                        EpisodeInfo(
                            episodeUI = info.toEpisodeUI(),
                            charactersUI = episodeCharacters?.map { it.toCharacterUI() },
                            isCharactersLoading = isCharactersLoading,
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
fun EpisodesScreenPreview() {
    RickAndMortyTheme {
        EpisodeScreen(episodeId = 1)
    }
}
