package com.atakanmadanoglu.musicapp.presentation.model

data class MusicUI(
    val id: Int,
    val musicName: String,
    val duration: Int,
    val imageUrl: String,
    val musicUrl: String,
    val liked: Boolean
)