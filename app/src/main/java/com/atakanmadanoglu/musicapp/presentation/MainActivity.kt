package com.atakanmadanoglu.musicapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.atakanmadanoglu.musicapp.presentation.navigation.MusicAppNavHost
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
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute =
                    backStackEntry?.destination?.route ?: Screen.MusicCategoryScreen.route
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MusicAppNavHost(
                       navController = navController,
                       currentRoute = currentRoute
                   )
                }
            }
        }
    }
}