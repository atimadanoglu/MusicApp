package com.atakanmadanoglu.musicapp.data.datasource.local

import com.atakanmadanoglu.musicapp.data.service.local.MusicDao
import com.atakanmadanoglu.musicapp.data.service.local.MusicEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMusicDataSourceImp @Inject constructor(
    private val musicDao: MusicDao
): LocalMusicDataSource {
    override fun getMusics(): Flow<List<MusicEntity>> = musicDao.getMusics()

    override suspend fun addMusic(
        musicEntity: MusicEntity
    ) = musicDao.addMusic(musicEntity)

    override suspend fun deleteMusicById(
        musicId: Int
    ) = musicDao.deleteMusicById(musicId)
}