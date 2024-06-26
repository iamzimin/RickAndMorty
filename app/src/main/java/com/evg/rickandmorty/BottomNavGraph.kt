package com.evg.rickandmorty

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.evg.characters.presentation.CharacterScreen
import com.evg.characters.presentation.CharactersScreen
import com.evg.episodes.presentation.EpisodeScreen
import com.evg.episodes.presentation.EpisodesScreen
import com.evg.locations.presentation.LocationScreen
import com.evg.locations.presentation.LocationsScreen

@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Characters.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        //Navigation
        composable(route = BottomBarScreen.Episodes.route) {
            EpisodesScreen()
        }
        composable(route = BottomBarScreen.Characters.route) {
            CharactersScreen()
        }
        composable(route = BottomBarScreen.Locations.route) {
            LocationsScreen()
        }

        //Deep links
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
            val id = entry.arguments?.getInt("id") ?: -1
            CharacterScreen(characterId = id)
        }
        composable(
            route = "episode/{id}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://rickandmortyapi.com/api/episode/{id}"
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
            val id = entry.arguments?.getInt("id") ?: -1
            EpisodeScreen(episodeId = id)
        }
        composable(
            route = "location/{id}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://rickandmortyapi.com/api/location/{id}"
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
            val id = entry.arguments?.getInt("id") ?: -1
            LocationScreen(locationId = id)
        }
    }
}