package com.evg.rickandmorty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object Episodes : BottomBarScreen(
        route = "episodes",
        title = "Episodes",
        icon = Icons.Default.DateRange
    )
    data object Characters : BottomBarScreen(
        route = "characters",
        title = "Characters",
        icon = Icons.Default.Face
    )
    data object Locations : BottomBarScreen(
        route = "locations",
        title = "Locations",
        icon = Icons.Default.Home
    )
}
