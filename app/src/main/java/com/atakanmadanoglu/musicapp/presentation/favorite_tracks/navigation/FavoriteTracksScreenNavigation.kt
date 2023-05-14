package com.atakanmadanoglu.musicapp.presentation.favorite_tracks.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.favorite_tracks.FavoriteTracksRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavGraphBuilder.favoriteTracksScreen(
    currentRoute: String,
    onBottomNavItemClicked: (String) -> Unit
) {
    composable(route = Screen.FavoriteTracksScreen.route) {
        FavoriteTracksRoute(
            currentRoute = currentRoute,
            onBottomNavItemClicked = {
                onBottomNavItemClicked(it)
            }
        )
    }
}