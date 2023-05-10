package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.data.datasource.local.LocalMusicDataSource
import com.atakanmadanoglu.musicapp.data.datasource.remote.RemoteMusicDataSource
import com.atakanmadanoglu.musicapp.data.service.local.MusicEntity
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicRepositoryImp @Inject constructor(
    private val remoteMusicDataSource: RemoteMusicDataSource,
    private val localMusicDataSource: LocalMusicDataSource
): MusicRepository {
    override suspend fun getGenres(): Genres =
        remoteMusicDataSource.getGenres()

    override suspend fun getArtistsByGenre(
        genreId: Int
    ): Artists = remoteMusicDataSource.getArtistsByGenre(genreId)

    override suspend fun getArtistById(
        artistId: Int
    ): Artist = remoteMusicDataSource.getArtistById(artistId)

    override suspend fun getArtistAlbumsById(
        artistId: Int
    ): Albums = remoteMusicDataSource.getArtistAlbumsById(artistId)

    override suspend fun getArtistTracksById(
        artistId: Int
    ): Tracks = remoteMusicDataSource.getArtistTracksById(artistId)

    override fun getMusics(): Flow<List<MusicEntity>> =
        localMusicDataSource.getMusics()

    override suspend fun addMusic(
        musicEntity: MusicEntity
    ) = localMusicDataSource.addMusic(musicEntity)

    override suspend fun deleteMusicById(
        musicId: Int
    ) = localMusicDataSource.deleteMusicById(musicId)
}