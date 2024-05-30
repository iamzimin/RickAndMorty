package com.evg.resource

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun CharacterCard(image: Int, name: String, status: String, lastLocation: String, firstSeen: String) {
    val cardHeight = 120.dp

    val nameSize = 16.sp
    val statusSize = 10.sp
    val headerSize = 10.sp
    val descriptionSize = 11.sp
    Card (
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight),
    ) {
        Row {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Image",
                modifier = Modifier.size(cardHeight),
            )
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Column {
                    Text(text = name, fontSize = nameSize)
                    Row {
                        Image(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "Status",
                            modifier = Modifier
                                .size(7.dp)
                                .clip(shape = CircleShape)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = status,
                            fontSize = statusSize,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Column {
                    Text(
                        text = "Last known location:",
                        fontSize = headerSize,
                        color = Color.Gray,
                    )
                    Text(
                        text = lastLocation,
                        fontSize = descriptionSize,
                    )
                }
                Column {
                    Text(
                        text = "First seen in:",
                        fontSize = headerSize,
                        color = Color.Gray,
                    )
                    Text(
                        text = firstSeen,
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
                image = R.drawable.search,
                name = "Jerry Smith",
                status = "Alive - Human",
                lastLocation = "Earth (Replacement Dimension)",
                firstSeen = "Rick Potion #9")
        }
    }
}