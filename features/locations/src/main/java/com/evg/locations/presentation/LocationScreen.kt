package com.evg.locations.presentation

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.locations.presentation.mapper.toCharacterUI
import com.evg.locations.presentation.mapper.toLocationUI
import com.evg.locations.presentation.viewmodel.LocationViewModel
import com.evg.resource.CustomSwipeRefreshIndicator
import com.evg.resource.NoInternetConnection
import com.evg.resource.NotFound
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.LazyColumnNoInfoPadding
import com.evg.resource.theme.RickAndMortyTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun LocationScreen(
    locationId: Int,
    viewModel: LocationViewModel = hiltViewModel<LocationViewModel>(),
) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val locationInfo by viewModel.locationInfo.collectAsState()
    val locationCharacters by viewModel.locationCharacters.collectAsState()
    val isInfoLoading by viewModel.isInfoLoading.collectAsState()
    val isResidentsLoading by viewModel.isResidentsLoading.collectAsState()

    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)

    if (!isInitialized) {
        LaunchedEffect(locationId) {
            viewModel.getLocationInfo(locationId)
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
            onRefresh = { viewModel.getLocationInfo(locationId) },
            indicator = { state, trigger ->
                CustomSwipeRefreshIndicator(
                    state = state,
                    trigger = trigger,
                )
            },
        ) {
            if (isInfoLoading) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                if (locationInfo == null) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = LazyColumnNoInfoPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        NotFound(
                            imageSize = 200,
                            textStyle = MaterialTheme.typography.titleLarge,
                            pageName = "Location",
                        )
                    }
                } else {
                    locationInfo?.let { info ->
                        LocationInfo(
                            locationUI = info.toLocationUI(),
                            charactersUI = locationCharacters?.map { it.toCharacterUI() },
                            isResidentsLoading = isResidentsLoading,
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
fun LocationScreenPreview() {
    RickAndMortyTheme {
        LocationScreen(locationId = 1)
    }
}
