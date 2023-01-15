package com.raju.data

import com.raju.data.repository.JokesDataRepository
import com.raju.domain.repository.JokesRepository
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
    fun provideJokesDataRepository(jokesDataRepository: JokesDataRepository): JokesRepository {
        return jokesDataRepository
    }

}