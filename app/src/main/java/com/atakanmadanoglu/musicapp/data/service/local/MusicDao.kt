package com.atakanmadanoglu.musicapp.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {
    @Query("SELECT * FROM music")
    fun getMusics(): Flow<List<MusicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMusic(musicEntity: MusicEntity)

    @Query("DELETE FROM music WHERE id = :musicId")
    suspend fun deleteMusicById(musicId: Int)
}