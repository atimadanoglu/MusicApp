package com.atakanmadanoglu.musicapp.data.datasource.local

import com.atakanmadanoglu.musicapp.data.service.local.MusicEntity
import kotlinx.coroutines.flow.Flow

interface LocalMusicDataSource {
    fun getMusics(): Flow<List<MusicEntity>>
    suspend fun addMusic(musicEntity: MusicEntity)
    suspend fun deleteMusicById(musicId: Int)
}