package com.atakanmadanoglu.musicapp.presentation.album_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.musicapp.domain.usecase.AddTrackUseCase
import com.atakanmadanoglu.musicapp.domain.usecase.DeleteMusicByIdUseCase
import com.atakanmadanoglu.musicapp.domain.usecase.GetAlbumByIdUseCase
import com.atakanmadanoglu.musicapp.domain.usecase.GetFavoriteTracksUseCase
import com.atakanmadanoglu.musicapp.presentation.album_details.navigation.AlbumDetailsArgs
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import com.atakanmadanoglu.musicapp.presentation.model.TrackUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AlbumDetailsUiState(
    val tracks: List<TrackUI> = listOf(),
    val favoriteTracks: List<FavoriteTrackUI> = listOf(),
    val albumName: String = "",
    val trackImage: String = "",
    val currentTrackWillBePlayed: String = "",
    val playAudio: Boolean = false,
    val synchronize: Boolean = false
)


@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
    private val addTrackUseCase: AddTrackUseCase,
    private val deleteMusicByIdUseCase: DeleteMusicByIdUseCase,
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
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
                    trackImage = album.coverMedium,
                    synchronize = true
                )
            }
        }
    }

    fun getFavoriteTracks() {
        viewModelScope.launch {
            getFavoriteTracksUseCase().collectLatest { list ->
                _albumsDetailsUiState.update { state ->
                    state.copy(favoriteTracks = list)
                }
                println("iç -> ${list.size}")
            }
        }
    }

    fun synchronizeData() {
        val favoriteTracksIds = _albumsDetailsUiState.value.favoriteTracks.map { it.id }
        val updatedTrackUIs = _albumsDetailsUiState.value.tracks.map { trackUI ->
            if (!favoriteTracksIds.contains(trackUI.id)) {
                trackUI
            } else {
                trackUI.copy(liked = true)
            }
        }

        _albumsDetailsUiState.update { currentState ->
            currentState.copy(tracks = updatedTrackUIs)
        }

    }

    fun addTrack(trackUI: TrackUI) {
        val favoriteTrackUI = FavoriteTrackUI(
            id = trackUI.id,
            musicName = trackUI.title,
            duration = trackUI.duration,
            musicUrl = trackUI.preview,
            imageUrl = _albumsDetailsUiState.value.trackImage
        )
        setLikedField(trackUI.id, true)
        viewModelScope.launch {
            addTrackUseCase(favoriteTrackUI)
        }
    }

    fun deleteTrack(id: Long) {
        setLikedField(id, false)
        viewModelScope.launch {
            deleteMusicByIdUseCase(id)
        }
    }

    private fun setLikedField(id: Long, value: Boolean) {
        val updatedList = _albumsDetailsUiState.value.tracks
            .map {
                if (it.id != id) {
                    it
                } else
                    it.copy(liked = value)
            }
        _albumsDetailsUiState.update {
            it.copy(tracks = updatedList)
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