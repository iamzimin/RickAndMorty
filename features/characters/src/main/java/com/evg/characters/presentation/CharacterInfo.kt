package com.evg.characters.presentation

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.evg.characters.R
import com.evg.resource.CharacterCard
import com.evg.resource.CharacterCardShimmer
import com.evg.resource.EpisodeCard
import com.evg.resource.EpisodeCardShimmer
import com.evg.resource.InfoCard
import com.evg.resource.LocationCardShimmer
import com.evg.resource.NoInternetConnection
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI
import com.evg.resource.model.character.EpisodeUI
import com.evg.resource.model.character.color
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.LazyColumnNoInfoPadding
import com.evg.resource.theme.RickAndMortyTheme
import com.evg.resource.theme.VerticalSpacerPadding
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterInfo(
    characterUI: CharacterUI,
    episodesUI: List<EpisodeUI>?,
    isEpisodesLoading: Boolean,
) {
    Column {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = characterUI.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(BorderRadius))
                                .align(alignment = Alignment.CenterHorizontally)
                        ) {
                            SubcomposeAsyncImage(
                                model = characterUI.image,
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .clip(shape = RoundedCornerShape(BorderRadius))
                                /*.align(alignment = Alignment.CenterHorizontally)*/,
                                contentDescription = characterUI.image,
                                contentScale = ContentScale.FillWidth,
                                loading = {
                                    Box(
                                        modifier = Modifier
                                            .size(300.dp)
                                            .shimmer(),
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color.LightGray)
                                        )
                                    }
                                },
                                error = {
                                    Image(
                                        modifier = Modifier
                                            .background(
                                                if (isSystemInDarkTheme()) {
                                                    Color.DarkGray
                                                } else {
                                                    Color.LightGray
                                                }
                                            )
                                            .scale(0.5f),
                                        painter = painterResource(id = com.evg.resource.R.drawable.file_error),
                                        contentDescription = "File Error",
                                        colorFilter = ColorFilter
                                            .tint(
                                                if (isSystemInDarkTheme()) {
                                                    Color.Gray
                                                } else {
                                                    Color.DarkGray
                                                }
                                            ),
                                    )
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(topStart = BorderRadius))
                                    .background(characterUI.status.color())
                                    .align(Alignment.BottomEnd)
                            ) {
                                /*Box(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .size(7.dp)
                                        .clip(CircleShape)
                                        .background(characterUI.status.color)
                                        .align(Alignment.CenterVertically)
                                )*/
                                Text(
                                    text = stringResource(id = characterUI.status.naming),
                                    modifier = Modifier
                                        /*.border(
                                            width = 2.dp,
                                            color = characterUI.status.color,
                                            shape = CircleShape
                                        )*/
                                        .padding(7.dp)
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(VerticalSpacerPadding))

                        Row {
                            InfoCard(
                                header = "Species",
                                content = characterUI.species,
                                modifier = Modifier.weight(1f),
                            )
                            Spacer(modifier = Modifier.width(VerticalSpacerPadding))
                            InfoCard(
                                header = "Gender",
                                content = stringResource(id = characterUI.gender.gender),
                                modifier = Modifier.weight(1f),
                            )
                        }

                        Spacer(modifier = Modifier.height(VerticalSpacerPadding))

                        Row {
                            InfoCard(
                                header = "Origin",
                                content = characterUI.origin.name,
                                modifier = Modifier.weight(1f),
                                isClickable = true,
                                link = characterUI.origin.url
                            )
                            Spacer(modifier = Modifier.padding(5.dp))
                            InfoCard(
                                header = "Type",
                                content = characterUI.type.ifEmpty { "None" },
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(VerticalSpacerPadding))

                    Text(
                        text = "Episodes (${characterUI.episode.size})",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                if (isEpisodesLoading) {
                    items(characterUI.episode.size) {
                        EpisodeCardShimmer()
                    }
                } else {
                    if (episodesUI == null) {
                        item {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = LazyColumnNoInfoPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                NoInternetConnection(
                                    imageSize = 100,
                                    textStyle = MaterialTheme.typography.bodyLarge,
                                )
                            }
                        }
                    } else {
                        items(episodesUI) { episode ->
                            EpisodeCard(episodeUI = episode)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharacterInfoPreview() {
    RickAndMortyTheme {
        CharacterInfo(
            characterUI = CharacterUI(
                id = 1,
                name = "Rick Saaaaaaaaaaaaaaa aaaaaaaaaaaaaanchez",
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
            episodesUI = listOf(
                EpisodeUI(
                    id = 1,
                    name = "Name 1",
                    air_date = "July 26, 2015",
                    episode = Pair(1, 2),
                    characters = listOf(
                        "https://rickandmortyapi.com/api/character/1",
                        "https://rickandmortyapi.com/api/character/2",
                    ),
                    url = "https://rickandmortyapi.com/api/episode/12"
                ),
                EpisodeUI(
                    id = 9,
                    name = "Name 2",
                    air_date = "November 29, 2016",
                    episode = Pair(3, 6),
                    characters = listOf(
                        "https://rickandmortyapi.com/api/character/5",
                        "https://rickandmortyapi.com/api/character/6",
                    ),
                    url = "https://rickandmortyapi.com/api/episode/35"
                )
            ),
            isEpisodesLoading = false,
        )
    }
}

