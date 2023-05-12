package com.atakanmadanoglu.musicapp.presentation.artist_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.musicapp.domain.usecase.GetArtistAlbumsByIdUseCase
import com.atakanmadanoglu.musicapp.domain.usecase.GetArtistByIdUseCase
import com.atakanmadanoglu.musicapp.presentation.artist_details.navigation.ArtistDetailsScreenArg
import com.atakanmadanoglu.musicapp.presentation.model.AlbumUI
import com.atakanmadanoglu.musicapp.presentation.model.ArtistUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ArtistDetailsUIState(
    val artist: ArtistUI = ArtistUI(),
    val albums: List<AlbumUI> = listOf(),
    val topBarTitle: String = ""
)

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(
    private val getArtistByIdUseCase: GetArtistByIdUseCase,
    private val getArtistAlbumsByIdUseCase: GetArtistAlbumsByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val artistDetailsArgs = ArtistDetailsScreenArg(savedStateHandle)

    private val _artistDetailsUiState = MutableStateFlow(ArtistDetailsUIState())
    val artistDetailsUiState get() = _artistDetailsUiState.asStateFlow()

    fun getArtistById() {
        viewModelScope.launch {
            val artist = getArtistByIdUseCase(artistDetailsArgs.artistId)
            _artistDetailsUiState.update {
                it.copy(
                    artist = artist,
                    topBarTitle = artist.name
                )
            }
        }
    }

    fun getArtistAlbumsById() {
        viewModelScope.launch {
            val albums = getArtistAlbumsByIdUseCase(artistDetailsArgs.artistId)
            _artistDetailsUiState.update {
                it.copy(albums = albums)
            }
        }
    }
}