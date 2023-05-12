package com.atakanmadanoglu.musicapp.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {
    @Query("SELECT * FROM track")
    fun getMusics(): Flow<List<FavoriteTrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMusic(favoriteTrackEntity: FavoriteTrackEntity)

    @Query("DELETE FROM track WHERE id = :musicId")
    suspend fun deleteMusicById(musicId: Int)
}