package com.atakanmadanoglu.musicapp.data.service.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class FavoriteTrackEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "music_name")
    val musicName: String,
    val duration: Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val musicUrl: String,
    val liked: Boolean
)