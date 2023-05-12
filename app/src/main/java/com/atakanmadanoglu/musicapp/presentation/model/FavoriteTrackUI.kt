package com.atakanmadanoglu.musicapp.presentation.model

data class FavoriteTrackUI(
    val id: Int = 0,
    val musicName: String = "",
    val duration: Int = 0,
    val imageUrl: String = "",
    val musicUrl: String = "",
    val liked: Boolean = false
)