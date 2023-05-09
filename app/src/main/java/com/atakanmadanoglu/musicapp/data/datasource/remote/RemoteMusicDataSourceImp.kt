package com.atakanmadanoglu.musicapp.data.datasource.remote

import com.atakanmadanoglu.musicapp.data.service.remote.MusicService
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Albums
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artist
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Artists
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genres
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Tracks
import javax.inject.Inject

class RemoteMusicDataSourceImp @Inject constructor(
    private val musicService: MusicService
) : RemoteMusicDataSource{
    override suspend fun getGenres(): Genres =
        musicService.getGenres()

    override suspend fun getArtistsByGenre(
        genreId: Int
    ): Artists = musicService.getArtistsByGenre(genreId)

    override suspend fun getArtistById(
        artistId: Int
    ): Artist = musicService.getArtistById(artistId)

    override suspend fun getArtistAlbumsById(
        artistId: Int
    ): Albums = musicService.getArtistAlbumsById(artistId)

    override suspend fun getArtistTracksById(
        artistId: Int
    ): Tracks = musicService.getArtistTracksById(artistId)
}