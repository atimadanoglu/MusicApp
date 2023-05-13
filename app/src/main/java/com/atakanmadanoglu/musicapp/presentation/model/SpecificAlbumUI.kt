package com.atakanmadanoglu.musicapp.presentation.model

data class SpecificAlbumUI(
    val id: Long,
    val title: String,
    val coverMedium: String,
    val releaseDate: String,
    val tracks: TracksUI
)