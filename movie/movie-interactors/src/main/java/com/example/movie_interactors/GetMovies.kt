package com.example.movie_interactors

import com.example.core.constants.Constants.PAGINATION_PAGE_SIZE
import com.example.core.domain_core.DataState
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent
import com.example.movie_datasource.cache.MovieCache
import com.example.movie_datasource.network.MovieService
import com.example.movie_domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetMovies(
    private val cache: MovieCache,
    private val service: MovieService,
) {

    fun execute(
        page: Int,
        api_key: String
    ): Flow<DataState<List<Movie>>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            kotlinx.coroutines.delay(3000)

            val movies: List<Movie> = try {
                service.getMoviesList(page = page, api_key = api_key)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Response<List<Movie>>(
                        uiComponent = UIComponent.Dialog(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown error"
                        )
                    )
                )
                listOf()
            }

            cache.insert(movies)


            val cachedMovies = cache.getAllMovies(page=page)

            emit(DataState.Data(cachedMovies))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<Movie>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}