package com.example.ui_moviedetail.di

import com.example.movie_interactors.GetMovieDetail
import com.example.movie_interactors.MovieInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {
    @Provides
    @Singleton
    fun provideGetMovieFromCache(
        interactors: MovieInteractors
    ): GetMovieDetail {
        return interactors.getMovieDetail
    }
}