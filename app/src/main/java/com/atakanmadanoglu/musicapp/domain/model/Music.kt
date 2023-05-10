package com.atakanmadanoglu.musicapp.domain.model

data class Music(
    val id: Int,
    val musicName: String,
    val duration: Int,
    val imageUrl: String,
    val musicUrl: String,
    val liked: Boolean
)