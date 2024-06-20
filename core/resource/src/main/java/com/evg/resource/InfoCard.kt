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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.EdgesMargin
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun InfoCard(
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = EdgesMargin),
            verticalArrangement = Arrangement.SpaceEvenly,
            ) {
            Text(
                text = header,
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Left,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = content,
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Left,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            /*Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(5.dp)
            ) {
                //Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = header,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    overflow = TextOverflow.Ellipsis
                )
                //Spacer(modifier = Modifier.weight(1f))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.primary)
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
            }*/
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoCardPreview() {
    RickAndMortyTheme {
        InfoCard(
            header = "Origin",
            content = "Earth (C-137)",
            modifier = Modifier,
            isClickable = true,
            link = "https://rickandmortyapi.com/api/character/1"
        )
    }
}