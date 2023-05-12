package com.atakanmadanoglu.musicapp.data.service.remote.dto

data class AlbumDTO(
    val id: Int,
    val title: String,
    val cover: String,
    val release_date: String,
    val tracklist: String
)