package com.atakanmadanoglu.musicapp.domain.model

data class Track(
    val id: Long,
    val title: String,
    val duration: Int,
    val preview: String
)