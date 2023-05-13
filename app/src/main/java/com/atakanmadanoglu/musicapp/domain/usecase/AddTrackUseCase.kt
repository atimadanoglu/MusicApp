package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import javax.inject.Inject

class AddTrackUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    suspend operator fun invoke(
        favoriteTrackUI: FavoriteTrackUI
    ) {
        val music = musicMapper.mapToFavoriteTrackDomain(favoriteTrackUI)
        musicRepository.addFavoriteTrack(music)
    }
}