package com.atakanmadanoglu.musicapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.atakanmadanoglu.musicapp.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val iconId: Int,
    @DrawableRes val secondaryIconId: Int,
    @StringRes val contentDescription: Int
) {
    object Category: BottomNavItem(
        route = Screen.MusicCategoryScreen.route,
        iconId = R.drawable.icons8_music,
        secondaryIconId = R.drawable.icons8_music,
        contentDescription = R.string.music_category
    )

    object Favorites: BottomNavItem(
        route = Screen.FavoriteTracksScreen.route,
        iconId = R.drawable.heart_outlined,
        secondaryIconId = R.drawable.heart_filled,
        contentDescription = R.string.favorites
    )
}