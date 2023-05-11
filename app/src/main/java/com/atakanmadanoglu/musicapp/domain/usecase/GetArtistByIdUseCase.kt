package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import javax.inject.Inject

class GetArtistByIdUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(
        artistId: Int
    ) = repository.getArtistById(artistId)
}