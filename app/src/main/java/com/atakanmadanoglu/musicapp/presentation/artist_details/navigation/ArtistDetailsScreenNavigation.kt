package com.atakanmadanoglu.musicapp.presentation.artist_details.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.atakanmadanoglu.musicapp.presentation.artist_details.ArtistDetailsRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

private const val artistIdArg = "artistId"

internal class ArtistDetailsScreenArg(
    val artistId: Int
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(savedStateHandle[artistIdArg]) as Int
    )
}


fun NavController.navigateToArtistDetailsScreen(artistId: Int) {
    this.navigate(
        Screen.ArtistDetailsScreen.route + "/$artistId"
    )
}

fun NavGraphBuilder.artistDetailsScreen(
    onCardClicked: (Int) -> Unit
) {
    composable(
        route = (Screen.ArtistDetailsScreen.route +
                "/{$artistIdArg}"),
        arguments = listOf(
            navArgument(artistIdArg) {
                type = NavType.IntType
            }
        )
    ) {
        ArtistDetailsRoute(
            onAlbumCardClicked = {
                onCardClicked(it)
            }
        )
    }
}