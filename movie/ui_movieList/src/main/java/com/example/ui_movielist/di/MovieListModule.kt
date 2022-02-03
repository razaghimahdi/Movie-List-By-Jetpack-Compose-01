package com.example.ui_movielist.di

import com.example.movie_interactors.GetMovies
import com.example.movie_interactors.MovieInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieListModule {

    @Provides
    @Singleton
    fun provideGetMovies(
        interactors: MovieInteractors
    ):GetMovies{
        return interactors.getMovies
    }



}