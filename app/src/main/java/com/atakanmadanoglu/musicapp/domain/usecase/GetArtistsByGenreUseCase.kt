package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import com.atakanmadanoglu.musicapp.presentation.model.ArtistUI
import javax.inject.Inject

class GetArtistsByGenreUseCase @Inject constructor(
    private val repository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    suspend operator fun invoke(
        genreId: Int
    ): List<ArtistUI> = repository
        .getArtistsByGenre(genreId).map {
            musicMapper.mapToArtistUi(it)
        }
}