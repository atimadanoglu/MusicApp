package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.data.datasource.local.LocalMusicDataSource
import com.atakanmadanoglu.musicapp.data.datasource.remote.RemoteMusicDataSource
import com.atakanmadanoglu.musicapp.data.mapper.MusicEntityMapper
import com.atakanmadanoglu.musicapp.di.IoDispatcher
import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.SpecificAlbum
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
    override suspend fun getGenres(): List<Genre> = withContext(ioDispatcher) {
        remoteMusicDataSource.getGenres().map {
            musicEntityMapper.mapToGenreDomain(it)
        }
    }

    override suspend fun getArtistsByGenre(
        genreId: Long
    ): List<Artist> = withContext(ioDispatcher) {
        remoteMusicDataSource
            .getArtistsByGenre(genreId)
            .map {
                musicEntityMapper.mapToArtistDomain(it)
            }
    }

    override suspend fun getArtistById(
        artistId: Long
    ): Artist = withContext(ioDispatcher) {
        val artistDTO = remoteMusicDataSource.getArtistById(artistId)
        musicEntityMapper.mapToArtistDomain(artistDTO)
    }

    override suspend fun getArtistAlbumsById(
        artistId: Long
    ): List<Album> = withContext(ioDispatcher) {
        remoteMusicDataSource
            .getArtistAlbumsById(artistId)
            .map {
                musicEntityMapper.mapToAlbumDomain(it)
            }
    }

    override suspend fun getAlbumById(
        albumId: Long
    ): SpecificAlbum = withContext(ioDispatcher) {
        val albumDTO = remoteMusicDataSource.getAlbumById(albumId)
        musicEntityMapper.mapToSpecificAlbumDomain(albumDTO)
    }

    override fun getFavoriteTracks(): Flow<List<FavoriteTrack>> =
        localMusicDataSource.getMusics().map {
            it.map { trackEntity ->
                musicEntityMapper
                    .mapToFavoriteTrackDomain(trackEntity)
            }
        }.flowOn(ioDispatcher)

    override suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack) = withContext(ioDispatcher) {
        val musicEntity = musicEntityMapper
            .mapToFavoriteTrackEntity(favoriteTrack)
        localMusicDataSource.addMusic(musicEntity)
    }

    override suspend fun deleteMusicById(
        musicId: Long
    ) = withContext(ioDispatcher) {
        localMusicDataSource.deleteMusicById(musicId)
    }
}