package com.atakanmadanoglu.musicapp.di

import com.atakanmadanoglu.musicapp.data.datasource.local.LocalMusicDataSource
import com.atakanmadanoglu.musicapp.data.datasource.local.LocalMusicDataSourceImp
import com.atakanmadanoglu.musicapp.data.datasource.remote.RemoteMusicDataSource
import com.atakanmadanoglu.musicapp.data.datasource.remote.RemoteMusicDataSourceImp
import com.atakanmadanoglu.musicapp.data.repository.MusicRepository
import com.atakanmadanoglu.musicapp.data.repository.MusicRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsRemoteMusicDataSource(
        remoteMusicDataSourceImp: RemoteMusicDataSourceImp
    ): RemoteMusicDataSource

    @Binds
    fun bindsMusicRepository(
        musicRepositoryImp: MusicRepositoryImp
    ): MusicRepository

    @Binds
    fun bindsLocalMusicDataSource(
        localMusicDataSourceImp: LocalMusicDataSourceImp
    ): LocalMusicDataSource
}