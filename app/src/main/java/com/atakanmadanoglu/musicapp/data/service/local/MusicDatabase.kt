package com.atakanmadanoglu.musicapp.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atakanmadanoglu.musicapp.data.service.local.MusicDatabase.Companion.VERSION

@Database(
    entities = [MusicEntity::class],
    version = VERSION,
    exportSchema = true
)
abstract class MusicDatabase: RoomDatabase() {
    abstract fun getMusicDao(): MusicDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "music-database"
    }
}