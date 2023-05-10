package com.atakanmadanoglu.musicapp.di

import android.content.Context
import androidx.room.Room
import com.atakanmadanoglu.musicapp.data.service.local.MusicDao
import com.atakanmadanoglu.musicapp.data.service.local.MusicDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMusicDatabase(
        @ApplicationContext context: Context
    ): MusicDatabase = Room.databaseBuilder(
        context, MusicDatabase::class.java, MusicDatabase.DB_NAME
    ).build()

    @Provides
    fun provideMusicDao(
        musicDatabase: MusicDatabase
    ): MusicDao = musicDatabase.getMusicDao()
}