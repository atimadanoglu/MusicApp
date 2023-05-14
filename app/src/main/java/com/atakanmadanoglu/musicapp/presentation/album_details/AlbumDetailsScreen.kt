package com.atakanmadanoglu.musicapp.presentation.album_details

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.atakanmadanoglu.musicapp.R
import com.atakanmadanoglu.musicapp.presentation.model.TrackUI
import com.atakanmadanoglu.musicapp.presentation.music_categories.PageTitleTopAppBar
import kotlinx.coroutines.launch


@Composable
fun AlbumDetailsRoute(
    viewModel: AlbumDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.albumDetailsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.getFavoriteTracks()
        viewModel.getArtistTracks()
    }

    if (uiState.synchronize) {
        LaunchedEffect(key1 = Unit) {
            viewModel.synchronizeData()
        }
    }

    val addMessage = stringResource(id = R.string.track_added)
    val deleteMessage = stringResource(id = R.string.track_removed)

    AlbumDetailsScreen(
        albumName = uiState.albumName,
        tracks = uiState.tracks,
        onTrackClicked = {
            viewModel.setCurrentTrack(it)
            viewModel.setPlayAudio(true)
        },
        trackImage = uiState.trackImage,
        snackBarMessage = { name, liked ->
            val message = if (!liked) {
                "$name $addMessage"
            } else {
                "$name $deleteMessage"
            }
            message
        },
        onLikeButtonClicked = {
            if (!it.liked) {
                viewModel.addTrack(it)
            }
            else {
                viewModel.deleteTrack(it.id)
            }
        },
        trackUri = uiState.currentTrackWillBePlayed,
        playAudio = uiState.playAudio
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailsScreen(
    modifier: Modifier = Modifier,
    albumName: String,
    tracks: List<TrackUI>,
    onTrackClicked: (String) -> Unit,
    trackImage: String,
    snackBarMessage: (String, Boolean) -> String,
    onLikeButtonClicked: (trackUI: TrackUI) -> Unit,
    playAudio: Boolean,
    trackUri: String
) {
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PageTitleTopAppBar(title = albumName)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            TrackList(
                tracks = tracks,
                onTrackCardClicked = { uri ->
                    onTrackClicked(uri)
                },
                trackImage = trackImage,
                onLikeButtonClicked = { item ->
                    onLikeButtonClicked(item)
                    scope.launch {
                        snackBarState.showSnackbar(snackBarMessage(item.title, item.liked))
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
fun AudioPlayer(
    uri: String
) {
    val context = LocalContext.current
    val player = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(uri)
    player.setMediaItem(mediaItem)
    player.prepare()
    player.play()

    Surface(color = MaterialTheme.colorScheme.background) {
        AndroidView(
            factory = { PlayerView(context) },
            update = { it.player = player },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun TrackList(
    modifier: Modifier = Modifier,
    tracks: List<TrackUI>,
    onTrackCardClicked: (uri: String) -> Unit,
    onLikeButtonClicked: (trackUI: TrackUI) -> Unit,
    trackImage: String
) {
    LazyColumn(
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 24.dp)
    ) {
        items(
            items = tracks,
            key = { it.id }
        ) { track ->
            TrackCard(
                track = track,
                onTrackCardClicked = {
                    onTrackCardClicked(it)
                },
                trackImage = trackImage,
                onLikeButtonClicked = { item ->
                    onLikeButtonClicked(item)
                }
            )
        }
    }
}

@Composable
fun TrackCard(
    track: TrackUI,
    trackImage: String,
    onTrackCardClicked: (uri: String) -> Unit,
    onLikeButtonClicked: (trackUI: TrackUI) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
            .clickable { onTrackCardClicked(track.preview) },
        border = BorderStroke(1.dp, Color.Gray.copy(0.3f))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.weight(0.3f),
                model = trackImage,
                contentScale = ContentScale.FillBounds,
                contentDescription = stringResource(id = R.string.track_cover)
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                val min = track.duration / 60
                val sec = track.duration % 60
                val durationText = "$min:$sec\'\'"
                Text(
                    modifier = Modifier.padding(horizontal = 36.dp),
                    text = track.title,
                    maxLines = 2,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 36.dp),
                    maxLines = 2,
                    text = durationText,
                    fontSize = 10.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.1f)
                    .padding(top = 12.dp)
            ) {
                val imageId = if (track.liked) {
                    R.drawable.heart_filled
                } else {
                    R.drawable.heart_outlined
                }

                val colorFilter: ColorFilter = if (track.liked) {
                    ColorFilter.tint(Color.Red)
                } else {
                    ColorFilter.tint(Color.Gray)
                }

                Image(
                    modifier = Modifier.clickable { onLikeButtonClicked(track) },
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = R.string.like_button),
                    colorFilter = colorFilter
                )
            }
        }
    }
}