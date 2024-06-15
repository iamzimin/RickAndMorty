package com.evg.rickandmorty

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.evg.characters.presentation.CharacterScreen
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
        composable(
            route = "character/{id}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://rickandmortyapi.com/api/character/{id}"
                    action = Intent.ACTION_VIEW
                },
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: -1 //TODO
            CharacterScreen(characterId = id)
        }
    }
}