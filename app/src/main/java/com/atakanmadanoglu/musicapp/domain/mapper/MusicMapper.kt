package com.atakanmadanoglu.musicapp.domain.mapper

import com.atakanmadanoglu.musicapp.domain.model.Music
import com.atakanmadanoglu.musicapp.presentation.model.MusicUI
import javax.inject.Inject

class MusicMapper @Inject constructor() {
    fun mapToUi(
        music: Music
    ): MusicUI = with(music) {
        MusicUI(id, musicName, duration, imageUrl, musicUrl, liked)
    }

    fun mapToDomain(
        musicUI: MusicUI
    ): Music = with(musicUI) {
        Music(id, musicName, duration, imageUrl, musicUrl, liked)
    }
}