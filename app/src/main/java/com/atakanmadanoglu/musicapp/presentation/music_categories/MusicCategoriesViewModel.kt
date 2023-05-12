package com.atakanmadanoglu.musicapp.presentation.music_categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakanmadanoglu.musicapp.domain.usecase.GetGenresUseCase
import com.atakanmadanoglu.musicapp.presentation.model.GenreUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MusicCategoryUiState(
    val genres: List<GenreUI> = listOf()
)

@HiltViewModel
class MusicCategoriesViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
): ViewModel() {
    private val _musicCategoryUiState = MutableStateFlow(MusicCategoryUiState())
    val musicCategoryUiState get() = _musicCategoryUiState.asStateFlow()

    fun getGenres() {
        viewModelScope.launch {
            val genres = getGenresUseCase()
            _musicCategoryUiState.update {
                it.copy(genres = genres)
            }
        }
    }
}