package com.example.movielistbycompose01.di

import android.app.Application
import com.example.movie_interactors.MovieInteractors
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieInteractorsModule {

    @Provides
    @Singleton
    @Named("heroAndroidSqlDriver") // in case you had another SQL Delight db
    fun provideAndroidDriver(app: Application): SqlDriver {
       return AndroidSqliteDriver(
           schema = MovieInteractors.schema,
           context = app,
           name = MovieInteractors.dbName
       )
    }

    /**
     * Provide all the interactors in hero-interactors module
     */
    @Provides
    @Singleton
    fun provideMovieInteractors(
        @Named("heroAndroidSqlDriver") sqlDriver: SqlDriver,
    ): MovieInteractors{
        return MovieInteractors.build(sqlDriver)
    }
}