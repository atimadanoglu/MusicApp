package com.atakanmadanoglu.musicapp.presentation.album_details.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.atakanmadanoglu.musicapp.presentation.album_details.AlbumDetailsRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

private const val albumIdArg = "albumId"

internal class AlbumDetailsArgs(
    val albumId: Long
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(savedStateHandle[albumIdArg]) as Long
    )
}

fun NavController.navigateToAlbumDetails(
    albumId: Long
) {
    this.navigate(Screen.AlbumDetailsScreen.route + "/$albumId")
}

fun NavGraphBuilder.albumDetailsScreen() {
    composable(
        route = (Screen.AlbumDetailsScreen.route + "/{$albumIdArg}"),
        arguments = listOf(
            navArgument(albumIdArg) {
                type = NavType.LongType
            }
        )
    ) {
        AlbumDetailsRoute()
    }
}