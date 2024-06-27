package com.evg.resource

import android.app.PendingIntent
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.TaskStackBuilder
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI
import com.evg.resource.model.character.color
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.RickAndMortyTheme
import com.valentinilk.shimmer.shimmer
import kotlin.coroutines.coroutineContext

@Composable
fun CharacterCard(
    characterUI: CharacterUI
) {
    val navController = LocalNavHostController.current
    val cardHeight = 140.dp

    Card (
        shape = RoundedCornerShape(BorderRadius),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(vertical = 5.dp)
            .clip(shape = RoundedCornerShape(BorderRadius))
            .clickable {
                navController.navigate("character/${characterUI.id}")
            },
    ) {
        Row {
            SubcomposeAsyncImage(
                model = characterUI.image,
                modifier = Modifier.size(cardHeight),
                contentDescription = characterUI.image,
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillBounds, //Crop FillWidth
                loading = {
                    Box(
                        modifier = Modifier
                            .size(cardHeight)
                            .shimmer(),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(cardHeight)
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
                        painter = painterResource(id = R.drawable.file_error),
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
                },
            )

            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = EdgesMargin),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Column {
                    Text(
                        text = characterUI.name,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(characterUI.status.color())
                                /*.border(
                                    border = BorderStroke(
                                        width = 0.5.dp,
                                        color = MaterialTheme.colorScheme.outline
                                    ),
                                    shape = CircleShape
                                )*/
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = stringResource(id = characterUI.status.naming),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column {
                    Text(
                        text = "First seen in:",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )
                    Text(
                        text = characterUI.origin.name,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column {
                    Text(
                        text = "Last known location:",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )
                    Text(
                        text = characterUI.location.name,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharacterCardPreview() {
    RickAndMortyTheme {
        Column {
            CharacterCard(
                CharacterUI(
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
                        "https://rickandmortyapi.com/api/episode/3",
                        "https://rickandmortyapi.com/api/episode/4",
                        "https://rickandmortyapi.com/api/episode/5",
                        "https://rickandmortyapi.com/api/episode/6",
                        "https://rickandmortyapi.com/api/episode/7",
                    ),
                    url = "https://rickandmortyapi.com/api/character/1"
                )
            )
        }
    }
}