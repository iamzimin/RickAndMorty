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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.locations.presentation.mapper.toCharacterUI
import com.evg.locations.presentation.mapper.toLocationUI
import com.evg.locations.presentation.viewmodel.LocationViewModel
import com.evg.resource.NoInternetConnection
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.LazyColumnNoInfoPadding
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun LocationScreen(
    locationId: Int,
    viewModel: LocationViewModel = hiltViewModel<LocationViewModel>(),
) {
    val locationInfo by viewModel.locationInfo.collectAsState()
    val locationCharacters by viewModel.locationCharacters.collectAsState()
    val isInfoLoading by viewModel.isInfoLoading.collectAsState()
    val isResidentsLoading by viewModel.isResidentsLoading.collectAsState()

    LaunchedEffect(locationId) {
        viewModel.getLocationInfo(locationId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = EdgesMargin),
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
                    NoInternetConnection(
                        imageSize = 200,
                        textStyle = MaterialTheme.typography.titleLarge,
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LocationScreenPreview() {
    RickAndMortyTheme {
        LocationScreen(locationId = 1)
    }
}
