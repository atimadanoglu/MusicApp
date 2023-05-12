package com.atakanmadanoglu.musicapp.presentation.artist_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.artist_list.ArtistListRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavController.navigateToArtistDetailsScreen() {
    this.navigate(Screen.ArtistDetailsScreen.route)
}

fun NavGraphBuilder.artistDetailsScreen() {
    composable(route = Screen.ArtistListScreen.route) {
        ArtistListRoute(onCardClicked = {})
    }
}