package com.atakanmadanoglu.musicapp.presentation.artist_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.musicapp.domain.usecase.GetArtistsByGenreUseCase
import com.atakanmadanoglu.musicapp.presentation.artist_list.navigation.ArtistListScreenArgs
import com.atakanmadanoglu.musicapp.presentation.model.ArtistUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArtistListUiState(
    val artists: List<ArtistUI> = listOf(),
    val topAppBarTitle: String = ""
)

@HiltViewModel
class ArtistListScreenViewModel @Inject constructor(
    private val getArtistsByGenreUseCase: GetArtistsByGenreUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val artistListArgs = ArtistListScreenArgs(savedStateHandle)

    private val _artistListUiState = MutableStateFlow(ArtistListUiState())
    val artistListUiState: StateFlow<ArtistListUiState> get() = _artistListUiState

    init {
        _artistListUiState.update {
            it.copy(topAppBarTitle = artistListArgs.genreName)
        }
    }

    fun getArtists() {
        viewModelScope.launch {
            _artistListUiState.update {
                it.copy(
                    artists = getArtistsByGenreUseCase(artistListArgs.genreId)
                )
            }
        }
    }

}