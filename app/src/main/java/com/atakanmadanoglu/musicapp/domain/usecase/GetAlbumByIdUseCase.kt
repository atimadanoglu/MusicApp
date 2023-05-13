package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import com.atakanmadanoglu.musicapp.presentation.model.SpecificAlbumUI
import javax.inject.Inject

class GetAlbumByIdUseCase @Inject constructor(
    private val repository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    suspend operator fun invoke(
        albumId: Long
    ): SpecificAlbumUI {
        val album = repository.getAlbumById(albumId)
        return musicMapper.mapToSpecificAlbumUi(album)
    }
}