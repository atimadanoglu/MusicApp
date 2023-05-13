package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.SpecificAlbum
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getArtistsByGenre(genreId: Long): List<Artist>
    suspend fun getArtistById(artistId: Long): Artist
    suspend fun getArtistAlbumsById(artistId: Long): List<Album>
    suspend fun getAlbumById(albumId: Long): SpecificAlbum
    fun getFavoriteTracks(): Flow<List<FavoriteTrack>>
    suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack)
    suspend fun deleteMusicById(musicId: Long)
}