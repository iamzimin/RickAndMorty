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
fun CharacterCardShimmer() {
    val cardHeight = 140.dp
    val textHeight = 12.dp
    val shimmerColor = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray

    Card (
        shape = RoundedCornerShape(BorderRadius),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(vertical = 5.dp)
            .clip(shape = RoundedCornerShape(BorderRadius)),
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(cardHeight)
                    .shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .size(cardHeight)
                        .background(shimmerColor)
                )
            }

            //Spacer(modifier = Modifier.width(EdgesMargin))

            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = EdgesMargin),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Column {
                    Box(
                        modifier = Modifier.shimmer(),
                    ) {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .width(150.dp)
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
                                .width(70.dp)
                                .clip(shape = RoundedCornerShape(BorderRadius))
                                .background(shimmerColor)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
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
                                .width(130.dp)
                                .clip(shape = RoundedCornerShape(BorderRadius))
                                .background(shimmerColor)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column {
                    Box(
                        modifier = Modifier.shimmer(),
                    ) {
                        Box(
                            modifier = Modifier
                                .height(textHeight)
                                .width(100.dp)
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
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharacterCardShimmerPreview() {
    RickAndMortyTheme {
        Column {
            CharacterCardShimmer()
        }
    }
}