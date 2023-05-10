package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke() = musicRepository.getGenres()
}