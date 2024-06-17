package com.evg.resource

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.theme.RickAndMortyTheme
import com.evg.resource.theme.VerticalSpacerPadding

@Composable
fun ImageTitle(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(bottom = VerticalSpacerPadding)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty),
            contentDescription = "Rick and Morty",
            modifier = Modifier
                .size(200.dp, 70.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ImageTitlePreview() {
    RickAndMortyTheme {
        ImageTitle(modifier = Modifier)
    }
}