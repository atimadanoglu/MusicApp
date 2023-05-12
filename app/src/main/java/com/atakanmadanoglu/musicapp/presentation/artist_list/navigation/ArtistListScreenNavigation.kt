package com.atakanmadanoglu.musicapp.presentation.artist_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.artist_list.ArtistListRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavController.navigateToArtistListScreen() {
    this.navigate(Screen.ArtistListScreen.route)
}

fun NavGraphBuilder.artistListScreen(

) {
    composable(route = Screen.ArtistListScreen.route) {
        ArtistListRoute(onCardClicked = {})
    }
}