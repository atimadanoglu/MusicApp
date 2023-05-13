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
    val artistId: Long
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(savedStateHandle[artistIdArg]) as Long
    )
}


fun NavController.navigateToArtistDetailsScreen(artistId: Long) {
    this.navigate(
        Screen.ArtistDetailsScreen.route + "/$artistId"
    )
}

fun NavGraphBuilder.artistDetailsScreen(
    onCardClicked: (Long) -> Unit
) {
    composable(
        route = (Screen.ArtistDetailsScreen.route +
                "/{$artistIdArg}"),
        arguments = listOf(
            navArgument(artistIdArg) {
                type = NavType.LongType
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