package com.atakanmadanoglu.musicapp.presentation.music_categories.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.music_categories.MusicCategoriesRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavController.navigateToMusicCategoriesScreen() {
    this.navigate(Screen.MusicCategoryScreen.route)
}

fun NavGraphBuilder.musicCategoriesScreen(
    currentRoute: String,
    onCardClicked: (id: Int) -> Unit
) {
    composable(route = Screen.MusicCategoryScreen.route) {
        MusicCategoriesRoute(
            currentRoute = currentRoute,
            onCardClicked = { onCardClicked(it) }
        )
    }
}