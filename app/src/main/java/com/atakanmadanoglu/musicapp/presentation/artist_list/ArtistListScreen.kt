package com.atakanmadanoglu.musicapp.presentation.artist_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atakanmadanoglu.musicapp.presentation.model.ArtistUI
import com.atakanmadanoglu.musicapp.presentation.music_categories.CardView
import com.atakanmadanoglu.musicapp.presentation.music_categories.PageTitleTopAppBar

@Composable
fun ArtistListRoute(
    onCardClicked: (itemId: Long) -> Unit,
    artistListScreenViewModel: ArtistListScreenViewModel = hiltViewModel()
) {
    val state by artistListScreenViewModel.artistListUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        artistListScreenViewModel.getArtists()
    }
    ArtistListScreen(
        artists = state.artists,
        onCardClicked = { id ->
            onCardClicked(id)
        },
        topBarTitle = state.topAppBarTitle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistListScreen(
    modifier: Modifier = Modifier,
    artists: List<ArtistUI>,
    onCardClicked: (itemId: Long) -> Unit,
    topBarTitle: String
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PageTitleTopAppBar(title = topBarTitle)
        }
    ) {
        ArtistListVerticalGridList(
            artists = artists,
            contentPaddingValues = it,
            onCardClicked = { id ->
                onCardClicked(id)
            }
        )
    }
}

@Composable
fun ArtistListVerticalGridList(
    artists: List<ArtistUI>,
    contentPaddingValues: PaddingValues,
    onCardClicked: (itemId: Long) -> Unit
) {
    LazyVerticalGrid(
        contentPadding = contentPaddingValues,
        columns = GridCells.Fixed(2)
    ) {
        items(
            items = artists,
            key = { it.id }
        ) { artist ->
            CardView(
                title = artist.name,
                imageUrl = artist.image,
                onItemClicked = {
                    onCardClicked(artist.id)
                }
            )
        }
    }
}