package com.atakanmadanoglu.musicapp.data.repository

import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks
import com.atakanmadanoglu.musicapp.domain.model.Music
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun getGenres(): Genres
    suspend fun getArtistsByGenre(genreId: Int): Artists
    suspend fun getArtistById(artistId: Int): Artist
    suspend fun getArtistAlbumsById(artistId: Int): Albums
    suspend fun getArtistTracksById(artistId: Int): Tracks
    fun getMusics(): Flow<List<Music>>
    suspend fun addMusic(music: Music)
    suspend fun deleteMusicById(musicId: Int)
}