package com.evg.resource

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.evg.resource.model.character.EpisodeUI
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun EpisodeCard(
    episodeUI: EpisodeUI,

) {
    val context = LocalContext.current
    val cardHeight = 120.dp
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(vertical = 5.dp)
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://rickandmortyapi.com/api/episode/${episodeUI.id}")
                )
                context.startActivity(intent)
            },
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = episodeUI.name)
            Row {
                Text(text = "Season: ")
                Text(text = episodeUI.episode.first.toString())
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Episode: ")
                Text(text = episodeUI.episode.second.toString())
            }
            Row {
                Text(text = "Characters in episode: ")
                Text(text = episodeUI.characters.size.toString())
            }
            Row {
                Text(text = "Air date: ")
                Text(text = episodeUI.air_date)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EpisodeCardPreview() {
    RickAndMortyTheme {
        Column {
            EpisodeCard(
                episodeUI = EpisodeUI(
                    id = 1,
                    name = "A Rickle in Time",
                    air_date = "July 26, 2015",
                    episode = Pair(1, 2),
                    characters = listOf(
                        "https://rickandmortyapi.com/api/character/1",
                        "https://rickandmortyapi.com/api/character/2",
                    ),
                    url = "https://rickandmortyapi.com/api/episode/12"
                )
            )
        }
    }
}