package com.atakanmadanoglu.musicapp.data.datasource.local

import com.atakanmadanoglu.musicapp.data.service.local.FavoriteTrackEntity
import kotlinx.coroutines.flow.Flow

interface LocalMusicDataSource {
    fun getMusics(): Flow<List<FavoriteTrackEntity>>
    suspend fun addMusic(favoriteTrackEntity: FavoriteTrackEntity)
    suspend fun deleteMusicById(musicId: Int)
}