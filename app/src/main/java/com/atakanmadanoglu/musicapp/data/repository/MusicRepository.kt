package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.data.service.local.MusicEntity
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun getGenres(): Genres
    suspend fun getArtistsByGenre(genreId: Int): Artists
    suspend fun getArtistById(artistId: Int): Artist
    suspend fun getArtistAlbumsById(artistId: Int): Albums
    suspend fun getArtistTracksById(artistId: Int): Tracks
    fun getMusics(): Flow<List<MusicEntity>>
    suspend fun addMusic(musicEntity: MusicEntity)
    suspend fun deleteMusicById(musicId: Int)
}