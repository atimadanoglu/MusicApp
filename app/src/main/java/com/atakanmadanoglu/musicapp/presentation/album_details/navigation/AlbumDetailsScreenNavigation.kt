package com.atakanmadanoglu.musicapp.presentation.album_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavController.navigateToAlbumDetails() {
    this.navigate(Screen.AlbumDetailsScreen.route)
}

fun NavGraphBuilder.albumDetailsScreen() {
    composable(route = Screen.ArtistListScreen.route) {
        //AlbumDetailsRoute()
    }
}