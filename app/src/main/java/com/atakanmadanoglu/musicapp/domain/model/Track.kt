package com.atakanmadanoglu.musicapp.domain.model

data class Track(
    val id: Int,
    val title: String,
    val duration: Int,
    val preview: String
)