package com.evg.characters.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.evg.resource.EpisodeCard
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI
import com.evg.resource.model.character.EpisodeUI
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.RickAndMortyTheme
import com.evg.resource.theme.VerticalSpacerPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterInfo(
    characterUI: CharacterUI,
    episodesUI: List<EpisodeUI>,
) {
    Column {
        Row {
            Text(
                text = characterUI.name,
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
                        //Row {
                        Text(
                            text = stringResource(id = characterUI.status.naming),
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = characterUI.status.color,
                                    shape = CircleShape
                                )
                                .padding(7.dp)
                        )
                        //}
                        Spacer(modifier = Modifier.height(VerticalSpacerPadding))
                        AsyncImage(
                            model = characterUI.image,
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .clip(shape = RoundedCornerShape(BorderRadius))
                                .align(alignment = Alignment.CenterHorizontally),
                            contentDescription = characterUI.image,
                            contentScale = ContentScale.FillWidth,
                        )

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
                        text = "Episodes",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                items(episodesUI) { episode ->
                    EpisodeCard(episode)
                }
            }
        }

    }
}

@Composable
private fun InfoCard(
    header: String,
    content: String,
    modifier: Modifier,
    isClickable: Boolean = false,
    link: String = "",
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(BorderRadius))
            .then(
                if (isClickable && link.isNotEmpty()) {
                    Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                        context.startActivity(intent)
                    }
                } else {
                    Modifier
                }
            )
            //.background(Color.Red),
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(5.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = header,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.LightGray)
                    .padding(10.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = content,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterInfoPreview() {
    RickAndMortyTheme {
        CharacterInfo(
            characterUI = CharacterUI(
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
            )
        )
    }
}

