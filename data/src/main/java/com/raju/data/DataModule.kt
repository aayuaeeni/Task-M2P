package com.raju.data

import com.raju.data.repository.PlaylistDataRepository
import com.raju.domain.repository.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providePlaylistDataRepository(playlistDataRepository: PlaylistDataRepository): PlaylistRepository {
        return playlistDataRepository
    }

}