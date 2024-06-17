package com.evg.resource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.model.FilterData
import com.evg.resource.model.FilterHeader
import com.evg.resource.theme.RickAndMortyTheme
import com.evg.resource.theme.VerticalSpacerPadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onApplyFilter: () -> Unit,
    header: String,
    filters: List<FilterHeader>,
) {
    val buttonSpace = 8.dp

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = header) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                filters.forEach { header ->
                    Column {
                        Text(header.title)
                        Spacer(modifier = Modifier.height(5.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(buttonSpace, Alignment.Start),
                        ) {
                            header.filters.forEach { filter ->
                                FilterButton(
                                    filterData = filter,
                                    modifier = Modifier
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(VerticalSpacerPadding))
                    }
                }
            }

        },
        dismissButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(buttonSpace)) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Clear")
                }
                Button(
                    onClick = onApplyFilter,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Apply")
                }
            }
        },
        confirmButton = { }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewFilterDialog() {
    RickAndMortyTheme {
        FilterDialog(
            onDismiss = {},
            onApplyFilter = {},
            header = "Filter characters",
            filters = listOf(
                FilterHeader(
                    title = "Status type",
                    filters = listOf(
                        FilterData(
                            text = "Alive",
                            color = Color.Green,
                            onClick = { },
                        ),
                        FilterData(
                            text = "Dead",
                            color = Color.Red,
                            onClick = { },
                        ),
                        FilterData(
                            text = "Unknown",
                            color = Color.Black,
                            onClick = { },
                        ),
                    )
                ),
                FilterHeader(
                    title = "Character type",
                    filters = listOf(
                        FilterData(
                            text = "Human",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Alien",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Humanoid",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Poopybutthole",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Mythological Creature",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Animal",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Robot",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Cronenberg",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Disease",
                            onClick = { },
                        ),
                        FilterData(
                            text = "Unknown",
                            onClick = { },
                        ),
                    )
                ),
                FilterHeader(
                    title = "Gender type",
                    filters = listOf(
                        FilterData(
                            text = "Female",
                            color = Color.Red,
                            onClick = { },
                        ),
                        FilterData(
                            text = "Male",
                            color = Color.Blue,
                            onClick = { },
                        ),
                        FilterData(
                            text = "Genderless",
                            color = Color.Yellow,
                            onClick = { },
                        ),
                        FilterData(
                            text = "Unknown",
                            color = Color.Black,
                            onClick = { },
                        ),
                    )
                ),
            ),
        )
    }
}