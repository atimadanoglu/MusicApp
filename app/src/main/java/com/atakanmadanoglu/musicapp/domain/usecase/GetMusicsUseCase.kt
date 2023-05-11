package com.atakanmadanoglu.musicapp.domain.usecase

import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.domain.mapper.MusicMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMusicsUseCase @Inject constructor(
    private val repository: MusicRepository,
    private val musicMapper: MusicMapper
) {
    operator fun invoke() = repository.getMusics().map {
        it.map { music -> musicMapper.mapToUi(music) }
    }
}