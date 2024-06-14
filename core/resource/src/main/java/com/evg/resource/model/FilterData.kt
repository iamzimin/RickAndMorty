package com.evg.resource.model

data class FilterData(
    val text: String,
    val onClick: () -> Unit
)
