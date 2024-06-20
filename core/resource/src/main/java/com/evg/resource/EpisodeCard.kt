package com.evg.resource

import android.content.Intent
import android.content.res.Configuration
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.evg.resource.model.character.EpisodeUI
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.RickAndMortyTheme
import com.evg.resource.theme.VerticalSpacerPadding

@Composable
fun EpisodeCard(
    episodeUI: EpisodeUI,
) {
    val context = LocalContext.current
    val cardHeight = 130.dp
    Card(
        shape = RoundedCornerShape(BorderRadius),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clip(shape = RoundedCornerShape(BorderRadius))
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
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = EdgesMargin),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = episodeUI.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Season: ",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )

                    Text(
                        text = episodeUI.episode.first.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            /*.align(Alignment.CenterHorizontally)*/,
                    )
                }

                Spacer(modifier = Modifier.width(VerticalSpacerPadding))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Episode: ",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )

                    Text(
                        text = episodeUI.episode.second.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            /*.align(Alignment.CenterHorizontally)*/,
                    )
                }

            }

            Column {
                Text(
                    text = "Air date:",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )

                Text(
                    text = episodeUI.air_date,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
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