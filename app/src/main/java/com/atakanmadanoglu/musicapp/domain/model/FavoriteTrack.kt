package com.atakanmadanoglu.musicapp.domain.model

data class FavoriteTrack(
    val id: Long,
    val musicName: String,
    val duration: Int,
    val imageUrl: String,
    val musicUrl: String,
    val liked: Boolean
)