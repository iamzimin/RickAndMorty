package com.evg.episodes.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.evg.episodes.presentation.mapper.toEpisodeUI
import com.evg.episodes.presentation.viewmodel.EpisodesViewModel
import com.evg.resource.CustomSwipeRefreshIndicator
import com.evg.resource.EpisodeCard
import com.evg.resource.EpisodeCardShimmer
import com.evg.resource.FragmentHeader
import com.evg.resource.theme.EdgesMargin
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = hiltViewModel<EpisodesViewModel>(),
) {
    val episodes = viewModel.episodes.collectAsLazyPagingItems()

    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)

    Column(
        modifier = Modifier.padding(horizontal = EdgesMargin)
    ) {
        FragmentHeader(
            onSearchTextChanged = { newName ->
                viewModel.setNameFilter(name = newName)
            },
        )

        when (episodes.loadState.refresh) {
            is LoadState.Loading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(10) {
                        EpisodeCardShimmer()
                    }
                }
            }
            is LoadState.Error -> { }
            is LoadState.NotLoading -> {
                SwipeRefresh(
                    state = refreshingState,
                    onRefresh = { viewModel.updateEpisodes() },
                    indicator = { state, trigger ->
                        CustomSwipeRefreshIndicator(
                            state = state,
                            trigger = trigger,
                        )
                    },
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(
                            count = episodes.itemCount,
                            key = episodes.itemKey { it.id },
                        ) { index ->
                            val item = episodes[index]
                            if (item != null) {
                                EpisodeCard(
                                    episodeUI = item.toEpisodeUI()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    EpisodesScreen()
}