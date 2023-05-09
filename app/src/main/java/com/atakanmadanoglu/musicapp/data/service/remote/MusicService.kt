package com.atakanmadanoglu.musicapp.data.service.remote

import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicService {
    @GET("/genre")
    suspend fun getGenres(): Genres

    @GET("/genre/{genre_id}/artists")
    suspend fun getArtistsByGenre(
        @Path("genre_id") genreId: Int
    ): Artists

    @GET("/artist/{artist_id}")
    suspend fun getArtistById(
        @Path("artist_id") artistId: Int
    ): Artist

    @GET("/artist/{artist_id}/top")
    suspend fun getArtistTracksById(
        @Path("artist_id") artistId: Int,
        @Query("limit") limit: Int = 50
    ): Tracks

    @GET("/artist/{artist_id}/albums")
    suspend fun getArtistAlbumsById(
        @Path("artist_id") artistId: Int
    ): Albums
}