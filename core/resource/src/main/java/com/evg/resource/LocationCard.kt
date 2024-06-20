package com.evg.resource

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.model.character.EpisodeUI
import com.evg.resource.model.character.LocationUI
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun LocationCard(
    locationUI: LocationUI,
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
                    Uri.parse("https://rickandmortyapi.com/api/location/${locationUI.id}")
                )
                context.startActivity(intent)
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = EdgesMargin),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = locationUI.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Column {
                Text(
                    text = "Type:",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )

                Text(
                    text = locationUI.type,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Column {
                Text(
                    text = "Dimension:",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )

                Text(
                    text = locationUI.dimension,
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
fun LocationCardPreview() {
    RickAndMortyTheme {
        Column {
            LocationCard(
                locationUI = LocationUI(
                    id = 1,
                    name = "A Rickle in Time",
                    type = "Planet",
                    dimension = "Dimension",
                    residents = listOf(
                        "https://rickandmortyapi.com/api/character/1",
                        "https://rickandmortyapi.com/api/character/2",
                    ),
                    url = "https://rickandmortyapi.com/api/location/12"
                )
            )
        }
    }
}