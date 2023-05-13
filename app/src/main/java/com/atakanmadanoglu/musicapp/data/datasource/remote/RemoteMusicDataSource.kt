package com.atakanmadanoglu.musicapp.data.datasource.remote

import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenreDTO

interface RemoteMusicDataSource {
    suspend fun getGenres(): List<GenreDTO>
    suspend fun getArtistsByGenre(genreId: Long): List<ArtistDTO>
    suspend fun getArtistById(artistId: Long): ArtistDTO
    suspend fun getArtistAlbumsById(artistId: Long): List<AlbumDTO>
   // suspend fun getArtistTracksById(artistId: Int): List<TrackDTO>
}