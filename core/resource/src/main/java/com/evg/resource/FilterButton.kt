package com.evg.resource

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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