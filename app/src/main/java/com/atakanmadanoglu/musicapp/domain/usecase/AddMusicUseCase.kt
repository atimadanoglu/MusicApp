package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import com.atakanmadanoglu.musicapp.presentation.model.MusicUI
import javax.inject.Inject

class AddMusicUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    suspend operator fun invoke(
        musicUI: MusicUI
    ) {
        val music = musicMapper.mapToDomain(musicUI)
        musicRepository.addMusic(music)
    }
}