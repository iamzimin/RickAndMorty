package com.evg.resource.model

import androidx.compose.ui.graphics.Color

data class FilterData(
    val text: String,
    val color: Color? = Color.Black,
    var selected: Boolean = false,
    val onClick: () -> Unit
)
