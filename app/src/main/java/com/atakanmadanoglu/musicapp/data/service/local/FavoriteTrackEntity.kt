package com.atakanmadanoglu.musicapp.data.service.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class FavoriteTrackEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "track_name")
    val trackName: String,
    val duration: Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val trackUrl: String,
    val liked: Boolean
)