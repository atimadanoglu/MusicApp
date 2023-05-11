package com.atakanmadanoglu.musicapp.presentation.navigation

import androidx.annotation.StringRes
import com.atakanmadanoglu.musicapp.R

/*
* I didn't add route string values into String resource because
* they don't need any translation into another languages. But titles
* will probably need translation into another languages
* */
sealed class Screen(val route: String, @StringRes val titleId: Int) {
    object MusicCategoryScreen: Screen("music_category", R.string.music_category)
    object ArtistListScreen: Screen("artist_list", R.string.artist_list)
    object ArtistDetailsScreen: Screen("artist_details", R.string.artist_details)
    object AlbumDetailsScreen: Screen("album_details", R.string.album_details)
    object LikedMusicsScreen: Screen("liked_musics", R.string.liked_musics)
}