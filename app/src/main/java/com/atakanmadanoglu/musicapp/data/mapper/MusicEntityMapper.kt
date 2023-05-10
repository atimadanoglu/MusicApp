package com.atakanmadanoglu.musicapp.data.mapper

import com.atakanmadanoglu.musicapp.data.service.local.MusicEntity
import com.atakanmadanoglu.musicapp.domain.model.Music
import javax.inject.Inject

class MusicEntityMapper @Inject constructor() {
    fun mapToDomain(
        musicEntity: MusicEntity
    ): Music = with(musicEntity) {
        Music(
            id = id,
            musicName = musicName,
            duration = duration,
            imageUrl = imageUrl,
            musicUrl = musicUrl,
            liked = liked
        )
    }

    fun mapToEntity(
        music: Music
    ): MusicEntity = with(music) {
        MusicEntity(
            id, musicName, duration, imageUrl, musicUrl, liked
        )
    }
}