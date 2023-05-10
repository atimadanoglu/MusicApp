package com.atakanmadanoglu.musicapp.data.datasource.remote

import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks

interface RemoteMusicDataSource {
    suspend fun getGenres(): Genres
    suspend fun getArtistsByGenre(genreId: Int): Artists
    suspend fun getArtistById(artistId: Int): Artist
    suspend fun getArtistAlbumsById(artistId: Int): Albums
    suspend fun getArtistTracksById(artistId: Int): Tracks
}