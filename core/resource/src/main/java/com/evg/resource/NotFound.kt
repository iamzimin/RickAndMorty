package com.evg.resource

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun NotFound(
    imageSize: Int,
    textStyle: TextStyle,
    pageName: String,
) {
    Column {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(imageSize.dp),
            painter = painterResource(id = R.drawable.not_found),
            contentDescription = "No WiFi",
            colorFilter = ColorFilter
                .tint(
                    if (isSystemInDarkTheme()) {
                        Color.Gray
                    } else {
                        Color.DarkGray
                    }
                ),
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "$pageName not found",
            style = textStyle,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotFoundPreview() {
    RickAndMortyTheme {
        NotFound(
            imageSize = 200,
            textStyle = MaterialTheme.typography.titleLarge,
            pageName = "Character",
        )
    }
}
