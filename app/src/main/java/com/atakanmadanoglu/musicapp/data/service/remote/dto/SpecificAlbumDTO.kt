package com.atakanmadanoglu.musicapp.data.service.remote.dto

data class SpecificAlbumDTO(
    val id: Long,
    val title: String,
    val cover_medium: String,
    val release_date: String,
    val tracks: TracksDTO
)
