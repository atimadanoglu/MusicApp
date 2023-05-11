package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import javax.inject.Inject

class GetArtistsByGenreUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(
        genreId: Int
    ) = repository.getArtistsByGenre(genreId)
}