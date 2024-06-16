package com.evg.characters.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun CharacterInfo(
    characterUI: CharacterUI,
    episodesUI: List<EpisodeUI>,
) {
    val cardHeight = 300.dp

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Column {
                Row(
                    //modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = characterUI.name,
                        fontSize = 24.sp,
                    )
                }
                Row {
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
                }
                Spacer(modifier = Modifier.height(20.dp))
                AsyncImage(
                    model = characterUI.image,
                    modifier = Modifier
                        .size(cardHeight)
                        .align(alignment = Alignment.CenterHorizontally),
                    contentDescription = characterUI.image,
                )

                Row {
                    InfoCard(
                        header = "Species",
                        content = characterUI.species,
                        modifier = Modifier.weight(1f),
                    )
                    InfoCard(
                        header = "Gender",
                        content = stringResource(id = characterUI.gender.gender),
                        modifier = Modifier.weight(1f),
                    )
                }

                Row {
                    InfoCard(
                        header = "Origin",
                        content = characterUI.origin.name,
                        modifier = Modifier.weight(1f),
                        isClickable = true,
                        link = characterUI.origin.url
                    )
                    InfoCard(
                        header = "Type",
                        content = characterUI.type.ifEmpty { "None" },
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Episodes",
                fontSize = 24.sp,
            )
        }

        items(episodesUI) { episode ->
            EpisodeCard(episode)
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
            .padding(5.dp)
            .clip(RoundedCornerShape(5.dp))
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
                Text(
                    text = header,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(20.dp)
            ) {
                Text(
                    text = content,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
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
                    name = "Earth (C-137)",
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

