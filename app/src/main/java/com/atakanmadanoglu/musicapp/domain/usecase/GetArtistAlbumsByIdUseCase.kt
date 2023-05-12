package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import javax.inject.Inject

class GetArtistAlbumsByIdUseCase @Inject constructor(
    private val repository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    suspend operator fun invoke(
        artistId: Int
    ) = repository.getArtistAlbumsById(artistId)
        .map { musicMapper.mapToAlbumUi(it) }
}