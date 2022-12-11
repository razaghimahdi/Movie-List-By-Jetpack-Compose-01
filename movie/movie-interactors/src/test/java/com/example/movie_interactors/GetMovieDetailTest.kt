package com.example.movie_interactors

import com.example.core.domain_core.DataState
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent
import com.example.movie_domain.Movie
import com.example.movielistbycompose01.movie_datasource_test.cache.MovieCacheFake
import com.example.movielistbycompose01.movie_datasource_test.cache.MovieDatabaseFake
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataValid
import com.example.movielistbycompose01.movie_datasource_test.network.serializeMovieData
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class GetMovieDetailTest {

    private lateinit var getMovieDetail: GetMovieDetail


    @Test
    fun getMovieFromCache_success() = runBlocking {
        // setup
        val MovieDatabase = MovieDatabaseFake()
        val MovieCache = MovieCacheFake(MovieDatabase)

        getMovieDetail = GetMovieDetail(MovieCache)

        // insert Movies into the cache
        val MovieData = serializeMovieData(MovieDataValid.data)
        MovieCache.insert(MovieData)

        // choose a Movie at random
        val Movie = MovieData.get(Random.nextInt(0, MovieData.size - 1))

        // Execute the use-case
        val emissions = getMovieDetail.execute(Movie.id).toList()

        // First emission should be loading
        assert(emissions[0] == DataState.Loading<Movie>(ProgressBarState.Loading))

        // Confirm second emission is data from the cache
        assert(emissions[1] is DataState.Data)
        assert((emissions[1] as DataState.Data).data?.id == Movie.id)
        assert((emissions[1] as DataState.Data).data?.title == Movie.title)

        // Confirm loading state is IDLE
        assert(emissions[2] == DataState.Loading<Movie>(ProgressBarState.Idle))
    }

    @Test
    fun getMovieFromCache_fail() = runBlocking {
        // setup
        val MovieDatabase = MovieDatabaseFake()
        val MovieCache = MovieCacheFake(MovieDatabase)

        getMovieDetail = GetMovieDetail(MovieCache)

        // insert Movies into the cache
        val MovieData = serializeMovieData(MovieDataValid.data)
        MovieCache.insert(MovieData)

        // choose a Movie at random and remove it from the cache
        val Movie = MovieData.get(Random.nextInt(0, MovieData.size - 1))
        MovieCache.removeMovie(Movie.id)

        // Execute the use-case
        val emissions = getMovieDetail.execute(Movie.id).toList()

        // First emission should be loading
        assert(emissions[0] == DataState.Loading<Movie>(ProgressBarState.Loading))

        // Confirm second emission is error response
        assert(emissions[1] is DataState.Response)
        assert(((emissions[1] as DataState.Response).uiComponent as UIComponent.Dialog).title == "Error")
        assert(
            ((emissions[1] as DataState.Response).uiComponent as UIComponent.Dialog).description.contains(
                "That Movie does not exist."
            )
        )

        // Confirm loading state is IDLE
        assert(emissions[2] == DataState.Loading<Movie>(ProgressBarState.Idle))
    }

}