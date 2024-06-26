package com.evg.episodes.presentation

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.evg.episodes.presentation.mapper.toEpisodeUI
import com.evg.episodes.presentation.viewmodel.EpisodesViewModel
import com.evg.resource.CharacterCard
import com.evg.resource.CharacterCardShimmer
import com.evg.resource.EpisodeCard
import com.evg.resource.EpisodeCardShimmer
import com.evg.resource.FragmentHeader
import com.evg.resource.R
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.IconSize


@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = hiltViewModel<EpisodesViewModel>(),
) {
    val episodes = viewModel.episodes.collectAsLazyPagingItems()

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

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    EpisodesScreen()
}