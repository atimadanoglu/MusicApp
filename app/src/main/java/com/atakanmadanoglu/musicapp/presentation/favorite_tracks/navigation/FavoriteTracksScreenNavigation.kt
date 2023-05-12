package com.atakanmadanoglu.musicapp.presentation.favorite_tracks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavController.navigateFavoriteTracksScreen() {
    this.navigate(Screen.FavoriteTracksScreen.route)
}

fun NavGraphBuilder.favoriteTracksScreen() {
    composable(route = Screen.FavoriteTracksScreen.route) {
        // FavoriteTracksRoute()
    }
}