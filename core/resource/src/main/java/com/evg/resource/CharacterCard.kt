package com.evg.resource

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.TaskStackBuilder
import coil.compose.AsyncImage
import com.evg.resource.model.character.CharacterGenderUI
import com.evg.resource.model.character.CharacterLocationUI
import com.evg.resource.model.character.CharacterOriginUI
import com.evg.resource.model.character.CharacterStatusUI
import com.evg.resource.model.character.CharacterUI
import com.evg.resource.theme.RickAndMortyTheme
import kotlin.coroutines.coroutineContext

@Composable
fun CharacterCard(
    characterUI: CharacterUI
) {
    val context = LocalContext.current
    val cardHeight = 120.dp

    val nameSize = 16.sp
    val statusSize = 10.sp
    val headerSize = 10.sp
    val descriptionSize = 11.sp
    Card (
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://rickandmortyapi.com/api/character/${characterUI.id}")
                )
                context.startActivity(intent)
                /*val pendingIntent = TaskStackBuilder.create(context).run {
                    addNextIntentWithParentStack(intent)
                    getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                }
                pendingIntent?.send()*/
            },
    ) {
        Row {
            AsyncImage(
                model = characterUI.image,
                modifier = Modifier.size(cardHeight),
                contentDescription = characterUI.image,
            )
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Column {
                    Text(text = characterUI.name, fontSize = nameSize)
                    Row {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(characterUI.status.color)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = stringResource(id = characterUI.status.naming),
                            fontSize = statusSize,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Column {
                    Text(
                        text = "First seen in:",
                        fontSize = headerSize,
                        color = Color.Gray,
                    )
                    Text(
                        text = characterUI.origin.name,
                        fontSize = descriptionSize,
                    )
                }
                Column {
                    Text(
                        text = "Last known location:",
                        fontSize = headerSize,
                        color = Color.Gray,
                    )
                    Text(
                        text = characterUI.location.name,
                        fontSize = descriptionSize,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
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