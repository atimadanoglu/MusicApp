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
private const val genreNameArg = "genreName"

internal class ArtistListScreenArgs(
    val genreId: Int,
    val genreName: String
) {
    constructor(savedStateHandle: SavedStateHandle):
            this(
                checkNotNull(savedStateHandle[genreIdArg]) as Int,
                checkNotNull(savedStateHandle[genreNameArg]) as String
            )
}

fun NavController.navigateToArtistListScreen(
    genreId: Int,
    genreName: String
) {
    this.navigate(
        route = Screen.ArtistListScreen.route +
                "/$genreId" + "/$genreName"
    )
}

fun NavGraphBuilder.artistListScreen(
    onCardClicked: (Int) -> Unit
) {
    composable(
        route = (Screen.ArtistListScreen.route +
                "/{$genreIdArg}" + "/{$genreNameArg}"),
        arguments = listOf(
            navArgument(genreIdArg) {
                type = NavType.IntType
            },
            navArgument(genreNameArg) {
                type = NavType.StringType
            }
        )
    ) {
        ArtistListRoute(
            onCardClicked = { id ->
                onCardClicked(id)
            }
        )
    }
}