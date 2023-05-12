package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun getGenres(): List<Genre>
    suspend fun getArtistsByGenre(genreId: Int): List<Artist>
    suspend fun getArtistById(artistId: Int): Artist
    suspend fun getArtistAlbumsById(artistId: Int): List<Album>
    suspend fun getArtistTracksById(artistId: Int): List<Track>
    fun getFavoriteTracks(): Flow<List<FavoriteTrack>>
    suspend fun addFavoriteTrack(favoriteTrack: FavoriteTrack)
    suspend fun deleteMusicById(musicId: Int)
}