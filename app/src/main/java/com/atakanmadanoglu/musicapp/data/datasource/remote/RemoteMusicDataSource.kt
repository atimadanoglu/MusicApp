package com.atakanmadanoglu.musicapp.data.datasource.remote

import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenreDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.TrackDTO

interface RemoteMusicDataSource {
    suspend fun getGenres(): List<GenreDTO>
    suspend fun getArtistsByGenre(genreId: Int): List<ArtistDTO>
    suspend fun getArtistById(artistId: Int): ArtistDTO
    suspend fun getArtistAlbumsById(artistId: Int): List<AlbumDTO>
    suspend fun getArtistTracksById(artistId: Int): List<TrackDTO>
}