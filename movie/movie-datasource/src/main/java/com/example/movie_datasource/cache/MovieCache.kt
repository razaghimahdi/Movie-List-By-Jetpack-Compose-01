package com.example.movie_datasource.cache

import com.example.movie_domain.Movie
import app.cash.sqldelight.db.SqlDriver

interface MovieCache {

    suspend fun insert(movie: Movie)

    suspend fun insert(movies: List<Movie>)

    suspend fun getMovie(id: Int): Movie?

    suspend fun removeMovie(id: Int)

    suspend fun getAllMovies(page: Int): List<Movie>

    companion object Factory {
        fun build(sqlDriver: SqlDriver): MovieCache {
            return MovieCacheImpl(MovieDatabase(sqlDriver))
        }

        val schema = MovieDatabase.Schema
        val dbName: String = "movies.db"
    }

}