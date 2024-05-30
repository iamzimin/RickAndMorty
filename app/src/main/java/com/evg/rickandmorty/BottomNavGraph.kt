package com.evg.rickandmorty

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.evg.characters.presentation.CharactersScreen
import com.evg.episodes.EpisodesScreen
import com.evg.locations.LocationsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Characters.route
    ) {
        composable(route = BottomBarScreen.Episodes.route) {
            EpisodesScreen()
        }
        composable(route = BottomBarScreen.Characters.route) {
            CharactersScreen()
        }
        composable(route = BottomBarScreen.Locations.route) {
            LocationsScreen()
        }
    }
}