package com.evg.resource

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
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
    val defaultTextColor = MaterialTheme.colorScheme.onSurface
    val buttonColor = if (filterData.selected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.primary


    Button(
        onClick = filterData.onClick,
        modifier = modifier,
        /*border = filterData.color?.let { color ->
            BorderStroke(2.dp, if (filterData.selected) color else Color.Transparent)
        },*/
        colors = ButtonColors(
            containerColor = buttonColor,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
        )
    ) {
        Text(
            text = filterData.text,
            color = /*if (filterData.selected) filterData.color ?: defaultTextColor else */defaultTextColor,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
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