package com.atakanmadanoglu.musicapp.data.datasource.local

import com.atakanmadanoglu.musicapp.data.service.local.FavoriteTrackEntity
import com.atakanmadanoglu.musicapp.data.service.local.MusicDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMusicDataSourceImp @Inject constructor(
    private val musicDao: MusicDao
): LocalMusicDataSource {
    override fun getMusics(): Flow<List<FavoriteTrackEntity>> = musicDao.getMusics()

    override suspend fun addMusic(
        favoriteTrackEntity: FavoriteTrackEntity
    ) = musicDao.addMusic(favoriteTrackEntity)

    override suspend fun deleteMusicById(
        musicId: Long
    ) = musicDao.deleteMusicById(musicId)
}