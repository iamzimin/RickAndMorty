package com.evg.resource

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

@Composable
fun ImageTitle(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(top = 50.dp, bottom = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty),
            contentDescription = "Rick and Morty",
            modifier = Modifier
                .size(200.dp, 70.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageTitlePreview() {
    RickAndMortyTheme {
        ImageTitle(modifier = Modifier)
    }
}