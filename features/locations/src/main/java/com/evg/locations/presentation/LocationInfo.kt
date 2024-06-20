package com.evg.locations.presentation

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.CharacterCard
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI
import com.evg.resource.model.character.LocationUI
import com.evg.resource.theme.RickAndMortyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationInfo(
    locationUI: LocationUI,
    charactersUI: List<CharacterUI>?,
) {
    Column {
        Row {
            Text(
                text = locationUI.name,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Characters",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }

                charactersUI?.let { characters ->
                    items(characters) { character ->
                        CharacterCard(characterUI = character)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LocationInfoPreview() {
    RickAndMortyTheme {
        LocationInfo(
            locationUI = LocationUI(
                id = 9,
                name = "Name 2",
                type = "Type",
                dimension = "dimension",
                residents = listOf(
                    "https://rickandmortyapi.com/api/character/5",
                    "https://rickandmortyapi.com/api/character/6",
                ),
                url = "https://rickandmortyapi.com/api/location/35"
            ),
            charactersUI = listOf(
                CharacterUI(
                    id = 1,
                    name = "Rick Sanchez",
                    status = CharacterStatusUI.ALIVE,
                    species = "Human",
                    type = "",
                    gender = CharacterGenderUI.MALE,
                    origin = CharacterOriginUI(
                        name = "Earth (C-137)ffffffffffffffffffffffff",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    location = CharacterLocationUI(
                        name = "Citadel of Ricks",
                        url = "https://rickandmortyapi.com/api/location/3"
                    ),
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    episode = listOf(
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2",
                    ),
                    url = "https://rickandmortyapi.com/api/character/1"
                ),
                CharacterUI(
                    id = 1,
                    name = "Rick Sanchez",
                    status = CharacterStatusUI.ALIVE,
                    species = "Human",
                    type = "",
                    gender = CharacterGenderUI.MALE,
                    origin = CharacterOriginUI(
                        name = "Earth (C-137)ffffffffffffffffffffffff",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    location = CharacterLocationUI(
                        name = "Citadel of Ricks",
                        url = "https://rickandmortyapi.com/api/location/3"
                    ),
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    episode = listOf(
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2",
                    ),
                    url = "https://rickandmortyapi.com/api/character/1"
                ),
            )
        )
    }
}
