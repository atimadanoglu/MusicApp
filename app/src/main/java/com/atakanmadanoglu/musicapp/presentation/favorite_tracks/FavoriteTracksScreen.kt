package com.atakanmadanoglu.musicapp.presentation.favorite_tracks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.atakanmadanoglu.musicapp.R
import com.atakanmadanoglu.musicapp.presentation.album_details.AudioPlayer
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import com.atakanmadanoglu.musicapp.presentation.music_categories.BottomNavigationBar
import com.atakanmadanoglu.musicapp.presentation.music_categories.PageTitleTopAppBar
import com.atakanmadanoglu.musicapp.presentation.navigation.BottomNavItem
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun FavoriteTracksRoute(
    currentRoute: String,
    onBottomNavItemClicked: (String) -> Unit,
    viewModel: FavoriteTracksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getFavoriteTracks()
    }

    FavoriteTracksScreen(
        tracks = uiState.tracks,
        onTrackClicked = {
            viewModel.setCurrentTrack(it)
            viewModel.setPlayAudio(true)
        },
        snackBarMessage = {
            val message = "$it is removed from your favorite tracks list"
            message
        },
        onLikeButtonClicked = {
            viewModel.deleteTrack(it)
        },
        trackUri = uiState.currentTrackWillBePlayed,
        playAudio = uiState.playAudio,
        currentRoute = currentRoute,
        onBottomNavItemClicked = {
            onBottomNavItemClicked(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTracksScreen(
    modifier: Modifier = Modifier,
    tracks: List<FavoriteTrackUI>,
    onTrackClicked: (String) -> Unit,
    snackBarMessage: (String) -> String,
    onLikeButtonClicked: (id: Long) -> Unit,
    playAudio: Boolean,
    trackUri: String,
    currentRoute: String,
    onBottomNavItemClicked: (String) -> Unit
) {
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PageTitleTopAppBar(title = stringResource(id = Screen.FavoriteTracksScreen.titleId))
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf(BottomNavItem.Category, BottomNavItem.Favorites),
                onBottomNavItemClicked = { onBottomNavItemClicked(it) },
                currentRoute = currentRoute,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            FavoriteTracksList(
                tracks = tracks,
                onTrackCardClicked = { uri ->
                    onTrackClicked(uri)
                },
                onLikeButtonClicked = { id, name ->
                    onLikeButtonClicked(id)
                    scope.launch {
                        snackBarState.showSnackbar(snackBarMessage(name))
                    }
                }
            )
        }
        if (playAudio) {
            AudioPlayer(
                uri = trackUri
            )
        }
    }
}

@Composable
fun FavoriteTracksList(
    modifier: Modifier = Modifier,
    tracks: List<FavoriteTrackUI>,
    onTrackCardClicked: (uri: String) -> Unit,
    onLikeButtonClicked: (id: Long, name: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 24.dp)
    ) {
        items(
            items = tracks,
            key = { it.id }
        ) { track ->
            FavoriteTrackCard(
                track = track,
                onTrackCardClicked = {
                    onTrackCardClicked(it)
                },
                onLikeButtonClicked = { id, name ->
                    onLikeButtonClicked(id, name)
                }
            )
        }
    }
}

@Composable
fun FavoriteTrackCard(
    track: FavoriteTrackUI,
    onTrackCardClicked: (uri: String) -> Unit,
    onLikeButtonClicked: (id: Long, name: String) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
            .clickable { onTrackCardClicked(track.musicUrl) },
        border = BorderStroke(1.dp, Color.Gray.copy(0.3f))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.weight(0.3f),
                model = track.imageUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = stringResource(id = R.string.track_cover)
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 36.dp),
                    text = track.musicName,
                    maxLines = 2,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 36.dp),
                    maxLines = 2,
                    text = track.duration.toString(),
                    fontSize = 10.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.1f)
                    .padding(top = 12.dp)
            ) {
                Image(
                    modifier = Modifier.clickable { onLikeButtonClicked(track.id, track.musicName) },
                    painter = painterResource(id = R.drawable.heart_filled),
                    contentDescription = stringResource(id = R.string.like_button),
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
        }
    }
}