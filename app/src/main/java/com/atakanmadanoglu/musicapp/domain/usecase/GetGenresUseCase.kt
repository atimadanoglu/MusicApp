package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    suspend operator fun invoke() =
        musicRepository
            .getGenres()
            .map { musicMapper.mapToGenreUi(it) }
}