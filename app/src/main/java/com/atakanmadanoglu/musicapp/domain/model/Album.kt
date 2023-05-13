package com.atakanmadanoglu.musicapp.domain.model

data class Album(
    val id: Long,
    val title: String,
    val cover: String,
    val releaseDate: String,
    val trackListUrl: String
)
