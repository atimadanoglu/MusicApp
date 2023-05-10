package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import javax.inject.Inject

class GetArtistAlbumsByIdUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(
        artistId: Int
    ) = repository.getArtistAlbumsById(artistId)
}