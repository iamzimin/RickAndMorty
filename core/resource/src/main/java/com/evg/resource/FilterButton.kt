package com.evg.resource

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.model.FilterData
import com.evg.resource.theme.RickAndMortyTheme

@Composable
fun FilterButton(filterData: FilterData, modifier: Modifier) {
    Button(
        onClick = filterData.onClick,
        modifier = modifier,
        border = filterData.color?.let { color ->
            BorderStroke(2.dp, if (filterData.selected) color else Color.Transparent)
        },
    ) {
        Text(
            text = filterData.text,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterButton() {
    RickAndMortyTheme {
        FilterButton(
            filterData = FilterData(
                onClick = {  },
                text = "Test",
                selected = true,
                color = Color.Red,
            ),
            modifier = Modifier,
        )
    }
}