package com.example.movie_interactors

import com.example.core.domain_core.DataState
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent
import com.example.movie_domain.Movie
import com.example.movielistbycompose01.movie_datasource_test.cache.MovieCacheFake
import com.example.movielistbycompose01.movie_datasource_test.cache.MovieDatabaseFake
import com.example.movielistbycompose01.movie_datasource_test.network.MovieServiceFake
import com.example.movielistbycompose01.movie_datasource_test.network.MovieServiceResponseType
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataValid
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataValid.NUM
import com.example.movielistbycompose01.movie_datasource_test.network.serializeMovieData
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * 1. Success (Retrieve a list of movies)
 * 2. Failure (Retrieve an empty list of movies)
 * 3. Failure (Retrieve malformed data (empty cache))
 * 4. Success (Retrieve malformed data but still returns data from cache)
 */
class GetMoviesTest {

    private lateinit var getMovies: GetMovies
/*
    @Test
    fun getMovies_success() = runBlocking {
        // setup
        val movieDatabase = MovieDatabaseFake()
        val movieCache = MovieCacheFake(movieDatabase)
        val movieService = MovieServiceFake.build(
            type = MovieServiceResponseType.GoodData
        )

        getMovies = GetMovies(
            cache = movieCache,
            service = movieService
        )

        // Confirm the cache is empty before any use-cases have been executed
        var cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.isEmpty())

        // Execute the use-case
        val emissions = getMovies.execute(1,"53387dc81a2da1494dd71b7168f2c455").toList()

        // First emission should be loading
        assert(emissions[0] == DataState.Loading<List<Movie>>(ProgressBarState.Loading))

        // Confirm second emission is data
        assert(emissions[1] is DataState.Data)
        assert(((emissions[1] as DataState.Data).data?.size ?: 0) == NUM)

        // Confirm the cache is no longer empty
        cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.size == NUM)

        // Confirm loading state is IDLE
        assert(emissions[2] == DataState.Loading<List<Movie>>(ProgressBarState.Idle))

    }

    @Test
    fun getMovies_emptyList() = runBlocking {
        // setup
        val movieDatabase = MovieDatabaseFake()
        val movieCache = MovieCacheFake(movieDatabase)
        val movieService = MovieServiceFake.build(
            type = MovieServiceResponseType.EmptyList
        )

        getMovies = GetMovies(
            cache = movieCache,
            service = movieService
        )

        // Confirm the cache is empty before any use-cases have been executed
        var cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.isEmpty())

        // Execute the use-case
        val emissions = getMovies.execute(1,"53387dc81a2da1494dd71b7168f2c455").toList()

        // First emission should be loading
        assert(emissions[0] == DataState.Loading<List<Movie>>(ProgressBarState.Loading))

        // Confirm second emission is data (empty list)
        assert(emissions[1] is DataState.Data)
        assert(((emissions[1] as DataState.Data).data?.size ?: 0) == 0)

        // Confirm the cache is STILL EMPTY
        cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.size == 0)

        // Confirm loading state is IDLE
        assert(emissions[2] == DataState.Loading<List<Movie>>(ProgressBarState.Idle))

    }

    @Test
    fun getMovies_malformedData() = runBlocking {
        // setup
        val movieDatabase = MovieDatabaseFake()
        val movieCache = MovieCacheFake(movieDatabase)
        val movieService = MovieServiceFake.build(
            type = MovieServiceResponseType.MalformedData
        )

        getMovies = GetMovies(
            cache = movieCache,
            service = movieService
        )

        // Confirm the cache is empty before any use-cases have been executed
        var cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.isEmpty())

        // Execute the use-case
        val emissions = getMovies.execute(1,"53387dc81a2da1494dd71b7168f2c455").toList()

        // First emission should be loading
        assert(emissions[0] == DataState.Loading<List<Movie>>(ProgressBarState.Loading))

        // Confirm second emission is error response
        assert(emissions[1] is DataState.Data)
        assert(((emissions[1] as DataState.Response).uiComponent as UIComponent.Dialog).title == "Network Data Error")
        assert(((emissions[1] as DataState.Response).uiComponent as UIComponent.Dialog).description.contains("Unexpected JSON token at offset"))

        // Confirm third emission is empty list of data
        assert(emissions[2] is DataState.Data)
        assert((emissions[2] as DataState.Data).data?.size == 0)

        // Confirm the cache is STILL EMPTY
        cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.isEmpty())

        // Confirm loading state is IDLE
        assert(emissions[3] == DataState.Loading<List<Movie>>(ProgressBarState.Idle))

    }

    @Test
    fun getMovies_malformedData_successFromCache() = runBlocking {
        // setup
        val movieDatabase = MovieDatabaseFake()
        val movieCache = MovieCacheFake(movieDatabase)
        val movieService = MovieServiceFake.build(
            type = MovieServiceResponseType.MalformedData
        )

        getMovies = GetMovies(
            cache = movieCache,
            service = movieService
        )

        // Confirm the cache is empty before any use-cases have been executed
        var cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.isEmpty())

        // Add some data to the cache by executing a successful request
        val movieData = serializeMovieData(MovieDataValid.data)
        movieCache.insert(movieData)

        // Confirm the cache is not empty anymore
        cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.size == NUM)


        // Execute the use-case
        val emissions = getMovies.execute(1,"53387dc81a2da1494dd71b7168f2c455").toList()

        // First emission should be loading
        assert(emissions[0] == DataState.Loading<List<Movie>>(ProgressBarState.Loading))

        // Confirm second emission is error response
        assert(emissions[1] is DataState.Data)
        assert(((emissions[1] as DataState.Response).uiComponent as UIComponent.Dialog).title == "Network Data Error")
        assert(((emissions[1] as DataState.Response).uiComponent as UIComponent.Dialog).description.contains("Unexpected JSON token at offset"))

        // Confirm third emission is data from the cache
        assert(emissions[2] is DataState.Data)
        assert((emissions[2] as DataState.Data).data?.size == NUM)

        // Confirm the cache is still not empty
        cachedMovies = movieCache.getAllMovies(1)
        assert(cachedMovies.size == NUM)

        // Confirm loading state is IDLE
        assert(emissions[3] == DataState.Loading<List<Movie>>(ProgressBarState.Idle))

    }

*/
}