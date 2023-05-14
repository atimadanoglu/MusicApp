package com.atakanmadanoglu.musicapp.domain.mapper

import com.atakanmadanoglu.musicapp.domain.model.Album
import com.atakanmadanoglu.musicapp.domain.model.Artist
import com.atakanmadanoglu.musicapp.domain.model.FavoriteTrack
import com.atakanmadanoglu.musicapp.domain.model.Genre
import com.atakanmadanoglu.musicapp.domain.model.SpecificAlbum
import com.atakanmadanoglu.musicapp.presentation.model.AlbumUI
import com.atakanmadanoglu.musicapp.presentation.model.ArtistUI
import com.atakanmadanoglu.musicapp.presentation.model.FavoriteTrackUI
import com.atakanmadanoglu.musicapp.presentation.model.GenreUI
import com.atakanmadanoglu.musicapp.presentation.model.SpecificAlbumUI
import com.atakanmadanoglu.musicapp.presentation.model.TrackUI
import com.atakanmadanoglu.musicapp.presentation.model.TracksUI
import javax.inject.Inject

class MusicMapper @Inject constructor() {
    fun mapToFavoriteTrackUI(
        favoriteTrack: FavoriteTrack
    ): FavoriteTrackUI = with(favoriteTrack) {
        FavoriteTrackUI(id, musicName, duration, imageUrl, musicUrl)
    }

    fun mapToFavoriteTrackDomain(
        favoriteTrackUI: FavoriteTrackUI
    ): FavoriteTrack = with(favoriteTrackUI) {
        FavoriteTrack(id, musicName, duration, imageUrl, musicUrl)
    }

    fun mapToAlbumUi(
        album: Album
    ): AlbumUI = with(album) {
        AlbumUI(id, title, coverMedium, releaseDate)
    }

    fun mapToSpecificAlbumUi(
        specificAlbum: SpecificAlbum
    ): SpecificAlbumUI = with(specificAlbum) {
        val tracks = tracks.data.map {
            TrackUI(it.id, it.title, it.duration, it.preview)
        }
        val tracksUI = TracksUI(tracks)
        SpecificAlbumUI(id, title, coverMedium, releaseDate, tracksUI)
    }

    fun mapToArtistUi(
        artist: Artist
    ): ArtistUI = with(artist) {
        ArtistUI(id, name, picture, trackListUrl)
    }

    fun mapToGenreUi(
        genre: Genre
    ): GenreUI = with(genre) {
        GenreUI(id, name, pictureUrl)
    }
}