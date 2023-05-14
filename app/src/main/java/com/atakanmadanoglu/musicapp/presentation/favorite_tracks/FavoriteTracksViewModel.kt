package com.atakanmadanoglu.musicapp.presentation.favorite_tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.musicapp.domain.usecase.DeleteMusicByIdUseCase
import com.atakanmadanoglu.musicapp.domain.usecase.GetFavoriteTracksUseCase
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteTracksUiState(
    val tracks: List<FavoriteTrackUI> = listOf(),
    val currentTrackWillBePlayed: String = "",
    val showSnackBar: Boolean = false,
    val playAudio: Boolean = false
)


@HiltViewModel
class FavoriteTracksViewModel @Inject constructor(
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
    private val deleteMusicByIdUseCase: DeleteMusicByIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteTracksUiState())
    val uiState get() = _uiState.asStateFlow()

    fun getFavoriteTracks() {
        viewModelScope.launch {
            getFavoriteTracksUseCase().collectLatest { list ->
                _uiState.update { state ->
                    state.copy(tracks = list)
                }
            }
        }
    }


    fun deleteTrack(id: Long) {
        viewModelScope.launch {
            deleteMusicByIdUseCase(id)
        }
    }

    fun setCurrentTrack(uri: String) {
        _uiState.update {
            it.copy(currentTrackWillBePlayed = uri)
        }
    }

    fun setPlayAudio(value: Boolean) {
        _uiState.update {
            it.copy(playAudio = value)
        }
    }
}