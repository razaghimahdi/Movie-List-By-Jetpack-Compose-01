package com.example.movie_interactors

import com.example.core.domain_core.DataState
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent
import com.example.movie_datasource.cache.MovieCache
import com.example.movie_domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetMovieDetail(
    private val cache: MovieCache
) {

    fun execute(
        id:Int
    ):Flow<DataState<Movie>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val cachedMovie = cache.getMovie(id=id) ?: throw Exception("That Movie does not exist.")

            emit(DataState.Data(cachedMovie))



        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<Movie>(
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