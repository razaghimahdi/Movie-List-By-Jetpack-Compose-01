package com.example.movielistbycompose01.movie_datasource_test.cache

import com.example.movie_datasource.cache.MovieCache
import com.example.movie_domain.Movie

class MovieCacheFake(
    private val db: MovieDatabaseFake
) : MovieCache {

    override suspend fun insert(movie: Movie) {
        if (db.movies.isNotEmpty()) {
            var didInsert = false
            for (m in db.movies) {
                if (m.id == movie.id) {
                    db.movies.remove(m)
                    db.movies.add(movie)
                    didInsert = true
                    break
                }
            }
            if (!didInsert) {
                db.movies.add(movie)
            }
        } else {
            db.movies.add(movie)
        }
    }

    override suspend fun insert(movies: List<Movie>) {
        if (db.movies.isNotEmpty()) {
            movies.forEach { movie ->
                if (db.movies.contains(movie)) {
                    db.movies.remove(movie)
                    db.movies.add(movie)
                }
            }
        } else {
            db.movies.addAll(movies)
        }
    }

    override suspend fun getMovie(id: Int): Movie? {
        return db.movies.find { it.id == id }
    }

    override suspend fun removeMovie(id: Int) {
        val movie = db.movies.filter { it.id == id }
        if (movie.isEmpty()) return
        db.movies.removeAll(movie)
    }

    override suspend fun getAllMovies(page: Int): List<Movie> {
        return db.movies
    }


}