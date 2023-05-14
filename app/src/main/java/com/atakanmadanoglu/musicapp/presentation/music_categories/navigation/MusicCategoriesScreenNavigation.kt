package com.atakanmadanoglu.musicapp.presentation.music_categories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atakanmadanoglu.musicapp.presentation.music_categories.MusicCategoriesRoute
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

fun NavGraphBuilder.musicCategoriesScreen(
    currentRoute: String,
    onBottomNavItemClicked: (String) -> Unit,
    onCardClicked: (itemId: Long, itemName: String) -> Unit
) {
    composable(route = Screen.MusicCategoryScreen.route) {
        MusicCategoriesRoute(
            currentRoute = currentRoute,
            onBottomNavItemClicked = {
                onBottomNavItemClicked(it)
            },
            onCardClicked = { id, name ->
                onCardClicked(id, name)
            }
        )
    }
}