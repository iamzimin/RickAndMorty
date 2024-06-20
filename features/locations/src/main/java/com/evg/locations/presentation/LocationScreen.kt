package com.evg.locations.presentation

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
import com.evg.locations.presentation.mapper.toCharacterUI
import com.evg.locations.presentation.mapper.toLocationUI
import com.evg.locations.presentation.viewmodel.LocationViewModel
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun LocationScreen(
    locationId: Int,
    viewModel: LocationViewModel = hiltViewModel<LocationViewModel>(),
) {
    val locationInfo by viewModel.locationInfo.collectAsState()
    val locationCharacters by viewModel.locationCharacters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(locationId) {
        viewModel.getLocationInfo(locationId)
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
            locationInfo?.let { info ->
                locationCharacters?.let { characters ->
                    LocationInfo(
                        locationUI = info.toLocationUI(),
                        charactersUI = characters.map { it.toCharacterUI() }
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
