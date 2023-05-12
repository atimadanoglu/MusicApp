package com.atakanmadanoglu.musicapp.data.service.remote

import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumsDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistsDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenresDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.TracksDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicService {
    @GET("/genre")
    suspend fun getGenres(): GenresDTO

    @GET("/genre/{genre_id}/artists")
    suspend fun getArtistsByGenre(
        @Path("genre_id") genreId: Int
    ): ArtistsDTO

    @GET("/artist/{artist_id}")
    suspend fun getArtistById(
        @Path("artist_id") artistId: Int
    ): ArtistDTO

    @GET("/artist/{artist_id}/top")
    suspend fun getArtistTracksById(
        @Path("artist_id") artistId: Int,
        @Query("limit") limit: Int = 50
    ): TracksDTO

    @GET("/artist/{artist_id}/albums")
    suspend fun getArtistAlbumsById(
        @Path("artist_id") artistId: Int
    ): AlbumsDTO
}