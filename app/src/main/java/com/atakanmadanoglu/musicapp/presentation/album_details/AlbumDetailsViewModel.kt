package com.atakanmadanoglu.musicapp.presentation.album_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.musicapp.domain.usecase.AddTrackUseCase
import com.atakanmadanoglu.musicapp.domain.usecase.GetAlbumByIdUseCase
import com.atakanmadanoglu.musicapp.presentation.album_details.navigation.AlbumDetailsArgs
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import com.atakanmadanoglu.musicapp.presentation.model.TrackUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AlbumDetailsUiState(
    val tracks: List<TrackUI> = listOf(),
    val albumName: String = "",
    val trackImage: String = "",
    val currentTrackWillBePlayed: String = "",
    val showSnackBar: Boolean = false,
    val playAudio: Boolean = false
)


@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
    private val addTrackUseCase: AddTrackUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val albumsDetailsArgs = AlbumDetailsArgs(savedStateHandle)

    private val _albumsDetailsUiState = MutableStateFlow(AlbumDetailsUiState())
    val albumDetailsUiState get() = _albumsDetailsUiState.asStateFlow()

    fun getArtistTracks() {
        viewModelScope.launch {
            val album = getAlbumByIdUseCase(albumsDetailsArgs.albumId)
            val tracks = album.tracks.data
            _albumsDetailsUiState.update {
                it.copy(
                    tracks = tracks,
                    albumName = album.title,
                    trackImage = album.coverMedium
                )
            }
        }
    }

    fun addTrack(trackUI: TrackUI) {
        val favoriteTrackUI = FavoriteTrackUI(
            id = trackUI.id,
            musicName = trackUI.title,
            duration = trackUI.duration,
            musicUrl = trackUI.preview
        )
        viewModelScope.launch {
            addTrackUseCase(favoriteTrackUI)
        }
    }

    fun setCurrentTrack(uri: String) {
        _albumsDetailsUiState.update {
            it.copy(currentTrackWillBePlayed = uri)
        }
    }

    fun setPlayAudio(value: Boolean) {
        _albumsDetailsUiState.update {
            it.copy(playAudio = value)
        }
    }
}