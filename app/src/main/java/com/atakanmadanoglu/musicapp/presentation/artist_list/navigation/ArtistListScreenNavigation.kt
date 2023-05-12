package com.atakanmadanoglu.musicapp.presentation.artist_list.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.atakanmadanoglu.musicapp.presentation.artist_list.ArtistListRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

private const val genreIdArg = "genreId"

internal class ArtistListScreenArgs(val genreId: Int) {
    constructor(savedStateHandle: SavedStateHandle):
            this(checkNotNull(savedStateHandle[genreIdArg]) as Int)
}

fun NavController.navigateToArtistListScreen(genreId: Int) {
    this.navigate(
        route = Screen.ArtistListScreen.route +
                "/$genreId"
    )
}

fun NavGraphBuilder.artistListScreen(

) {
    composable(
        route = Screen.ArtistListScreen.route + "/{$genreIdArg}",
        arguments = listOf(
            navArgument(genreIdArg) {
                type = NavType.IntType
            }
        )
    ) {
        ArtistListRoute(onCardClicked = {})
    }
}