package com.atakanmadanoglu.musicapp.data.mapper

import com.atakanmadanoglu.musicapp.data.service.local.FavoriteTrackEntity
import com.atakanmadanoglu.musicapp.data.service.remote.dto.AlbumDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.ArtistDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.GenreDTO
import com.atakanmadanoglu.musicapp.data.service.remote.dto.SpecificAlbumDTO
import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.SpecificAlbum
import com.atakanmadanoglu.musicapp.domain.model.Track
import com.atakanmadanoglu.musicapp.domain.model.Tracks
import javax.inject.Inject

class MusicEntityMapper @Inject constructor() {
    fun mapToFavoriteTrackDomain(
        trackEntity: FavoriteTrackEntity
    ): FavoriteTrack = with(trackEntity) {
        FavoriteTrack(id, trackName, duration, imageUrl, trackUrl)
    }

    fun mapToFavoriteTrackEntity(
        favoriteTrack: FavoriteTrack
    ): FavoriteTrackEntity = with(favoriteTrack) {
        FavoriteTrackEntity(
            id, musicName, duration, imageUrl, musicUrl
        )
    }

    fun mapToSpecificAlbumDomain(
        specificAlbumDTO: SpecificAlbumDTO
    ): SpecificAlbum = with(specificAlbumDTO) {
        val tracks = tracks.data.map {
            Track(it.id, it.title, it.duration, it.preview)
        }
        val tracksDomain = Tracks(tracks)
        SpecificAlbum(id, title, cover_medium, release_date, tracksDomain)
    }

    fun mapToAlbumDomain(
        albumDTO: AlbumDTO
    ): Album = with(albumDTO) {
        Album(id, title, cover_medium, release_date)
    }

    fun mapToArtistDomain(
        artistDTO: ArtistDTO
    ): Artist = with(artistDTO) {
        Artist(id, name, picture_medium, tracklist)
    }

    fun mapToGenreDomain(
        genreDTO: GenreDTO
    ): Genre = with(genreDTO) {
        Genre(id, name, picture)
    }
}