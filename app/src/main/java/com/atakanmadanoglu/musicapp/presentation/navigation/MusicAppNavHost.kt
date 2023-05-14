package com.atakanmadanoglu.musicapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.atakanmadanoglu.musicapp.presentation.album_details.navigation.albumDetailsScreen
import com.atakanmadanoglu.musicapp.presentation.album_details.navigation.navigateToAlbumDetails
import com.atakanmadanoglu.musicapp.presentation.artist_details.navigation.artistDetailsScreen
import com.atakanmadanoglu.musicapp.presentation.artist_details.navigation.navigateToArtistDetailsScreen
import com.atakanmadanoglu.musicapp.presentation.artist_list.navigation.artistListScreen
import com.atakanmadanoglu.musicapp.presentation.artist_list.navigation.navigateToArtistListScreen
import com.atakanmadanoglu.musicapp.presentation.favorite_tracks.navigation.favoriteTracksScreen
import com.atakanmadanoglu.musicapp.presentation.music_categories.navigation.musicCategoriesScreen

@Composable
fun MusicAppNavHost(
    navController: NavHostController,
    currentRoute: String
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MusicCategoryScreen.route
    ) {
        musicCategoriesScreen(
            currentRoute = currentRoute,
            onBottomNavItemClicked = { route ->
                navController.navigate(route)
            },
            onCardClicked = { genreId, genreName ->
                navController.navigateToArtistListScreen(genreId, genreName)
            }
        )
        artistListScreen(
            onCardClicked = { id ->
                navController.navigateToArtistDetailsScreen(id)
            }
        )
        favoriteTracksScreen(
            currentRoute = currentRoute,
            onBottomNavItemClicked = {
                navController.navigate(it)
            }
        )
        artistDetailsScreen(
            onCardClicked = { id->
                navController.navigateToAlbumDetails(id)
            }
        )
        albumDetailsScreen()
    }
}