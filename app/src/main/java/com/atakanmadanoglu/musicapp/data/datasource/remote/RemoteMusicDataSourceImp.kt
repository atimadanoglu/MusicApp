package com.atakanmadanoglu.musicapp.data.datasource.remote

import com.atakanmadanoglu.musicapp.data.service.remote.MusicService
import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenreDTO
import javax.inject.Inject

class RemoteMusicDataSourceImp @Inject constructor(
    private val musicService: MusicService
) : RemoteMusicDataSource{
    override suspend fun getGenres(): List<GenreDTO> =
        musicService.getGenres().data

    override suspend fun getArtistsByGenre(
        genreId: Long
    ): List<ArtistDTO> = musicService.getArtistsByGenre(genreId).data

    override suspend fun getArtistById(
        artistId: Long
    ): ArtistDTO = musicService.getArtistById(artistId)

    override suspend fun getArtistAlbumsById(
        artistId: Long
    ): List<AlbumDTO> = musicService.getArtistAlbumsById(artistId).data

    /*override suspend fun getArtistTracksById(
        artistId: Int
    ): List<TrackDTO> = musicService.getArtistTracksById(artistId).data*/
}