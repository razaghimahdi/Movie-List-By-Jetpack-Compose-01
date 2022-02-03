package com.example.movie_datasource.cache

import com.example.core.constants.Constants.PAGINATION_PAGE_SIZE
import com.example.movie_domain.Movie
import com.example.moviedatasource.cache.MovieDbQueries

class MovieCacheImpl(private val movieDatabase: MovieDatabase):
MovieCache{

    private var queries: MovieDbQueries = movieDatabase.movieDbQueries
    override suspend fun insert(movie: Movie) {

        return movie.run {
            queries.insert(
                id = id.toLong(),
                original_title = original_title,
                backdrop_path = backdrop_path,
                title = title,
                original_language = original_language,
                overview = overview,
                poster_path = poster_path,
                release_date = release_date,
                vote_average = vote_average,
                vote_count = vote_count.toLong(),
            )
        }
    }

    override suspend fun insert(movies: List<Movie>) {
        for(movie in movies){
            try {
                insert(movie)
            }catch (e: Exception){
                e.printStackTrace()
                // if one has an error just continue with others
            }
        }
    }

    override suspend fun getMovie(id: Int): Movie? {
        return queries.getMovie(id.toLong()).executeAsOne().toMovie()
    }

    override suspend fun removeMovie(id: Int)  {
         queries.remove(id.toLong())
    }

    override suspend fun getAllMovies(page:Int): List<Movie> {
        return queries.selectAll( pageSize =  PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) *  PAGINATION_PAGE_SIZE).toLong()).executeAsList().map { it.toMovie() }
    }


}