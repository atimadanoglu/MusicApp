package com.atakanmadanoglu.musicapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.atakanmadanoglu.musicapp.presentation.album_details.navigation.albumDetailsScreen
import com.atakanmadanoglu.musicapp.presentation.album_details.navigation.navigateToAlbumDetails
import com.atakanmadanoglu.musicapp.presentation.artist_details.navigation.artistDetailsScreen
import com.atakanmadanoglu.musicapp.presentation.artist_details.navigation.navigateToArtistDetailsScreen
import com.atakanmadanoglu.musicapp.presentation.artist_list.navigation.artistListScreen
import com.atakanmadanoglu.musicapp.presentation.artist_list.navigation.navigateToArtistListScreen
import com.atakanmadanoglu.musicapp.presentation.favorite_tracks.navigation.favoriteTracksScreen
import com.atakanmadanoglu.musicapp.presentation.music_categories.navigation.musicCategoriesScreen
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen
import com.atakanmadanoglu.musicapp.theme.MusicAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppTheme(
                darkTheme = false
            ) {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MusicCategoryScreen.route
                    ) {
                        val currentRoute =
                            backStackEntry.value?.destination?.route ?: Screen.MusicCategoryScreen.route
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
                        favoriteTracksScreen()
                        artistDetailsScreen(
                            onCardClicked = { id->
                                navController.navigateToAlbumDetails(id)
                            }
                        )
                        albumDetailsScreen()
                    }
                }
            }
        }
    }
}