package com.evg.characters.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.ImageTitle
import com.evg.resource.SearchField
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun FragmentHeader(
    onSearchTextChanged: (String) -> Unit,
    filterButton: @Composable() (() -> Unit)? = null,
) {
    Column {
        ImageTitle(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            SearchField(
                modifier = Modifier.weight(1f),
                onValueChange = onSearchTextChanged
            )
            Spacer(modifier = Modifier.width(5.dp))
            filterButton?.let { it() }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewFragmentHeader() {
    RickAndMortyTheme {
        FragmentHeader(
            onSearchTextChanged = {},
        )
    }
}