package com.example.movie_interactors

import com.example.movie_datasource.cache.MovieCache
import com.example.movie_datasource.network.MovieService
import app.cash.sqldelight.db.SqlDriver

data class MovieInteractors(
    val getMovies: GetMovies,
    val getMovieDetail: GetMovieDetail
) {
    companion object Factory {
        fun build(sqlDriver: SqlDriver): MovieInteractors {
            val service = MovieService.build()
            val cache = MovieCache.build(sqlDriver)
            return MovieInteractors(
                getMovies = GetMovies(service = service, cache = cache),
                getMovieDetail = GetMovieDetail(cache = cache)
            )
        }

        val schema = MovieCache.schema

        val dbName: String = MovieCache.dbName


    }
}
