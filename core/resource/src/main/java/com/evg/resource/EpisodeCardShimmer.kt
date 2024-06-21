package com.evg.resource

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.valentinilk.shimmer.shimmer

@Composable
fun EpisodeCardShimmer() {
    val context = LocalContext.current
    val cardHeight = 130.dp
    val textHeight = 12.dp
    val shimmerColor = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray

    Card(
        shape = RoundedCornerShape(BorderRadius),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clip(shape = RoundedCornerShape(BorderRadius))
            .padding(vertical = 5.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = EdgesMargin),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Box(
                modifier = Modifier.shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(170.dp)
                        .clip(shape = RoundedCornerShape(BorderRadius))
                        .background(shimmerColor)
                )
            }

            Row {
                Box(
                    modifier = Modifier.shimmer(),
                ) {
                    Box(
                        modifier = Modifier
                            .height(textHeight)
                            .width(70.dp)
                            .clip(shape = RoundedCornerShape(BorderRadius))
                            .background(shimmerColor)
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                Box(
                    modifier = Modifier.shimmer(),
                ) {
                    Box(
                        modifier = Modifier
                            .height(textHeight)
                            .width(70.dp)
                            .clip(shape = RoundedCornerShape(BorderRadius))
                            .background(shimmerColor)
                    )
                }
            }

            Column {
                Box(
                    modifier = Modifier.shimmer(),
                ) {
                    Box(
                        modifier = Modifier
                            .height(textHeight)
                            .width(70.dp)
                            .clip(shape = RoundedCornerShape(BorderRadius))
                            .background(shimmerColor)
                    )
                }

                Spacer(modifier = Modifier.height(1.dp))

                Box(
                    modifier = Modifier.shimmer(),
                ) {
                    Box(
                        modifier = Modifier
                            .height(textHeight)
                            .width(150.dp)
                            .clip(shape = RoundedCornerShape(BorderRadius))
                            .background(shimmerColor)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EpisodeCardShimmerPreview() {
    RickAndMortyTheme {
        Column {
            EpisodeCardShimmer()
        }
    }
}