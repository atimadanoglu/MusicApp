package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.data.datasource.local.LocalMusicDataSource
import com.atakanmadanoglu.musicapp.data.datasource.remote.RemoteMusicDataSource
import com.atakanmadanoglu.musicapp.data.mapper.MusicEntityMapper
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks
import com.atakanmadanoglu.musicapp.di.IoDispatcher
import com.atakanmadanoglu.musicapp.domain.model.Music
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicRepositoryImp @Inject constructor(
    private val remoteMusicDataSource: RemoteMusicDataSource,
    private val localMusicDataSource: LocalMusicDataSource,
    private val musicEntityMapper: MusicEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): MusicRepository {
    override suspend fun getGenres(): Genres = withContext(ioDispatcher) {
        remoteMusicDataSource.getGenres()
    }

    override suspend fun getArtistsByGenre(
        genreId: Int
    ): Artists = withContext(ioDispatcher) {
        remoteMusicDataSource.getArtistsByGenre(genreId)
    }

    override suspend fun getArtistById(
        artistId: Int
    ): Artist = withContext(ioDispatcher) {
        remoteMusicDataSource.getArtistById(artistId)
    }

    override suspend fun getArtistAlbumsById(
        artistId: Int
    ): Albums = withContext(ioDispatcher) {
        remoteMusicDataSource.getArtistAlbumsById(artistId)
    }

    override suspend fun getArtistTracksById(
        artistId: Int
    ): Tracks = withContext(ioDispatcher) {
        remoteMusicDataSource.getArtistTracksById(artistId)
    }

    override fun getMusics(): Flow<List<Music>> =
        localMusicDataSource.getMusics().map {
            it.map { musicEntity ->
                musicEntityMapper.mapToDomain(musicEntity)
            }
        }.flowOn(ioDispatcher)

    override suspend fun addMusic(
        music: Music
    ) = withContext(ioDispatcher) {
        val musicEntity = musicEntityMapper.mapToEntity(music)
        localMusicDataSource.addMusic(musicEntity)
    }

    override suspend fun deleteMusicById(
        musicId: Int
    ) = withContext(ioDispatcher) {
        localMusicDataSource.deleteMusicById(musicId)
    }
}