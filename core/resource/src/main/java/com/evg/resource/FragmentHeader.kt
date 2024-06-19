package com.evg.resource

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.IconSize
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun FragmentHeader(
    onSearchTextChanged: (String) -> Unit,
    filterButton: @Composable() (() -> Unit)? = null,
) {
    Column {
        ImageTitle(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
        Row {
            SearchField(
                modifier = Modifier.weight(1f),
                onValueChange = onSearchTextChanged
            )
            filterButton?.let {
                Spacer(modifier = Modifier.width(5.dp))
                it()
            }
        }
    }

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewFragmentHeader() {
    RickAndMortyTheme {
        FragmentHeader(
            onSearchTextChanged = {},
            filterButton = {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                            shape = RoundedCornerShape(BorderRadius),
                        )
                        .size(IconSize)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        )
    }
}