package com.atakanmadanoglu.musicapp.data.mapper

import com.atakanmadanoglu.musicapp.data.service.local.FavoriteTrackEntity
import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenreDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.TrackDTO
import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.Track
import javax.inject.Inject

class MusicEntityMapper @Inject constructor() {
    fun mapToFavoriteTrackDomain(
        trackEntity: FavoriteTrackEntity
    ): FavoriteTrack = with(trackEntity) {
        FavoriteTrack(id, trackName, duration, imageUrl, trackUrl, liked)
    }

    fun mapToFavoriteTrackEntity(
        favoriteTrack: FavoriteTrack
    ): FavoriteTrackEntity = with(favoriteTrack) {
        FavoriteTrackEntity(
            id, musicName, duration, imageUrl, musicUrl, liked
        )
    }

    fun mapToTrackDomain(
        trackDTO: TrackDTO
    ): Track = with(trackDTO) {
        Track(id, title, duration, preview)
    }

    fun mapToTrackDTO(
        track: Track
    ): TrackDTO = with(track) {
        TrackDTO(id, title, duration, preview)
    }

    fun mapToAlbumDomain(
        albumDTO: AlbumDTO
    ): Album = with(albumDTO) {
        Album(id, title, cover, release_date, tracklist)
    }

    fun mapToAlbumDTO(
        album: Album
    ): AlbumDTO = with(album) {
        AlbumDTO(id, title, cover, releaseDate, trackListUrl)
    }

    fun mapToArtistDomain(
        artistDTO: ArtistDTO
    ): Artist = with(artistDTO) {
        Artist(id, name, picture_medium, tracklist)
    }

    fun mapToArtistDTO(
        artist: Artist
    ): ArtistDTO = with(artist) {
        ArtistDTO(id, name, picture, trackListUrl)
    }

    fun mapToGenreDomain(
        genreDTO: GenreDTO
    ): Genre = with(genreDTO) {
        Genre(id, name, picture)
    }

    fun mapToGenreDTO(
        genre: Genre
    ): GenreDTO = with(genre) {
        GenreDTO(id, name, pictureUrl)
    }
}