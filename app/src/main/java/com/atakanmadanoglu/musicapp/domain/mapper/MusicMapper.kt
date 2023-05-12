package com.atakanmadanoglu.musicapp.domain.mapper

import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.Track
import com.atakanmadanoglu.musicapp.presentation.model.AlbumUI
import com.atakanmadanoglu.musicapp.presentation.model.ArtistUI
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import com.atakanmadanoglu.musicapp.presentation.model.GenreUI
import com.atakanmadanoglu.musicapp.presentation.model.TrackUI
import javax.inject.Inject

class MusicMapper @Inject constructor() {
    fun mapToFavoriteTrackUI(
        favoriteTrack: FavoriteTrack
    ): FavoriteTrackUI = with(favoriteTrack) {
        FavoriteTrackUI(id, musicName, duration, imageUrl, musicUrl, liked)
    }

    fun mapToFavoriteTrackDomain(
        favoriteTrackUI: FavoriteTrackUI
    ): FavoriteTrack = with(favoriteTrackUI) {
        FavoriteTrack(id, musicName, duration, imageUrl, musicUrl, liked)
    }

    fun mapToTrackUi(
        track: Track
    ): TrackUI = with(track) {
        TrackUI(id, title, duration, preview)
    }

    fun mapToTrackDomain(
        trackUI: TrackUI
    ): Track = with(trackUI) {
        Track(id, title, duration, preview)
    }

    fun mapToAlbumUi(
        album: Album
    ): AlbumUI = with(album) {
        AlbumUI(id, title, cover, releaseDate, trackListUrl)
    }

    fun mapToAlbumDomain(
        albumUI: AlbumUI
    ): Album = with(albumUI) {
        Album(id, title, cover, releaseDate, trackListUrl)
    }

    fun mapToArtistUi(
        artist: Artist
    ): ArtistUI = with(artist) {
        ArtistUI(id, name, picture, trackListUrl)
    }

    fun mapToArtistDomain(
        artistUi: ArtistUI
    ): Artist = with(artistUi) {
        Artist(id, name, picture, trackListUrl)
    }

    fun mapToGenreUi(
        genre: Genre
    ): GenreUI = with(genre) {
        GenreUI(id, name, pictureUrl)
    }

    fun mapToGenreDomain(
        genreUI: GenreUI
    ): Genre = with(genreUI) {
        Genre(id, name, pictureUrl)
    }
}