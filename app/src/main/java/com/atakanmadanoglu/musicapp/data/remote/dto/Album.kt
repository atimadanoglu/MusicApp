package com.atakanmadanoglu.musicapp.data.remote.dto

data class Album(
    val id: Int,
    val title: String,
    val cover: String,
    val release_date: String,
    val tracklist: String
)