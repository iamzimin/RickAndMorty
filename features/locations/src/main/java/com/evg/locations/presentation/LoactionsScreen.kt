package com.evg.locations.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.evg.locations.presentation.mapper.toLocationUI
import com.evg.locations.presentation.viewmodel.LocationViewModel
import com.evg.resource.FragmentHeader
import com.evg.resource.LocationCard
import com.evg.resource.LocationCardShimmer
import com.evg.resource.theme.EdgesMargin


@Composable
fun LocationsScreen(
    viewModel: LocationViewModel = hiltViewModel<LocationViewModel>(),
) {
    val locations = viewModel.locations.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.padding(horizontal = EdgesMargin)
    ) {
        FragmentHeader(
            onSearchTextChanged = { newName ->
                viewModel.setNameFilter(name = newName)
            },
        )

        when (locations.loadState.refresh) {
            is LoadState.Loading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(10) {
                        LocationCardShimmer()
                    }
                }
            }
            is LoadState.Error -> { }
            is LoadState.NotLoading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(
                        count = locations.itemCount,
                        key = locations.itemKey { it.id },
                    ) { index ->
                        val item = locations[index]
                        if (item != null) {
                            LocationCard(
                                locationUI = item.toLocationUI()
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
    LocationsScreen()
}