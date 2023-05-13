package com.atakanmadanoglu.musicapp.data.service.remote

import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumsDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistsDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenresDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.SpecificAlbumDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicService {
    @GET("/genre")
    suspend fun getGenres(): GenresDTO

    @GET("/genre/{genre_id}/artists")
    suspend fun getArtistsByGenre(
        @Path("genre_id") genreId: Long
    ): ArtistsDTO

    @GET("/artist/{artist_id}")
    suspend fun getArtistById(
        @Path("artist_id") artistId: Long
    ): ArtistDTO

    @GET("/album/{album_id}")
    suspend fun getAlbumById(
        @Path("album_id") albumId: Long
    ): SpecificAlbumDTO

    @GET("/artist/{artist_id}/albums")
    suspend fun getArtistAlbumsById(
        @Path("artist_id") artistId: Long
    ): AlbumsDTO
}