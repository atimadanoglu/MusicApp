package com.atakanmadanoglu.musicapp.presentation.artist_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.atakanmadanoglu.musicapp.R
import com.atakanmadanoglu.musicapp.presentation.model.AlbumUI
import com.atakanmadanoglu.musicapp.presentation.music_categories.PageTitleTopAppBar

@Composable
fun ArtistDetailsRoute(
    onAlbumCardClicked: (albumId: Long) -> Unit,
    viewModel: ArtistDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.artistDetailsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.getArtistById()
        viewModel.getArtistAlbumsById()
    }

    ArtistDetailsScreen(
        albums = uiState.albums,
        artistImageUrl = uiState.artist.image,
        artistName = uiState.artist.name,
        onAlbumCardClicked = { albumId -> onAlbumCardClicked(albumId) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailsScreen(
    modifier: Modifier = Modifier,
    albums: List<AlbumUI>,
    artistImageUrl: String,
    artistName: String,
    onAlbumCardClicked: (albumId: Long) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PageTitleTopAppBar(title = artistName)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ArtistImage(imageUrl = artistImageUrl)
            Spacer(modifier = modifier.height(8.dp))
            Divider(thickness = 1.dp)
            AlbumList(
                albums = albums,
                onAlbumCardClicked = { albumId ->
                    onAlbumCardClicked(albumId)
                }
            )
        }
    }
}

@Composable
fun ArtistImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        model = imageUrl,
        contentScale = ContentScale.Fit,
        contentDescription = stringResource(id = R.string.artist_image)
    )
}

@Composable
fun AlbumList(
    modifier: Modifier = Modifier,
    albums: List<AlbumUI>,
    onAlbumCardClicked: (albumId: Long) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 24.dp)
    ) {
        items(
            items = albums,
            key = { it.id }
        ) { album ->
            AlbumCard(
                album = album,
                onAlbumCardClicked = {
                    onAlbumCardClicked(it)
                }
            )
        }
    }
}

@Composable
fun AlbumCard(
    album: AlbumUI,
    onAlbumCardClicked: (albumId: Long) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
            .clickable { onAlbumCardClicked(album.id) },
        border = BorderStroke(1.dp, Color.Gray.copy(0.3f))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.weight(0.3f),
                model = album.coverMedium,
                contentScale = ContentScale.FillBounds,
                contentDescription = stringResource(id = R.string.album_cover)
            )
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(top = 16.dp, end = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier.padding(start = 36.dp),
                    text = album.title,
                    maxLines = 2,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(start = 36.dp),
                    text = album.releaseDate,
                    fontSize = 10.sp
                )
            }
        }
    }
}