package com.atakanmadanoglu.musicapp.domain.model

data class SpecificAlbum(
    val id: Long,
    val title: String,
    val coverMedium: String,
    val releaseDate: String,
    val tracks: Tracks
)