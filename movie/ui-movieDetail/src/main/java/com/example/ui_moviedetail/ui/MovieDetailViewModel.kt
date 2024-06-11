package com.example.ui_moviedetail.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.constants.Logger
import com.example.core.constants.Queue
import com.example.core.domain_core.DataState
import com.example.core.domain_core.UIComponent
import com.example.movie_interactors.GetMovieDetail
import com.example.ui_moviedetail.ui.state.MovieDetailEvents
import com.example.ui_moviedetail.ui.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(
    private val getMovieDetail: GetMovieDetail,
    private val savedStateHandle: SavedStateHandle,
    private val logger: Logger,
) : ViewModel() {

    val state: MutableState<MovieDetailState> = mutableStateOf(MovieDetailState())

    init {
        savedStateHandle.get<Int>("MovieId")?.let { MovieId ->

            onTriggerEvent(MovieDetailEvents.GetMovie(MovieId))
        }
    }

    fun onTriggerEvent(event: MovieDetailEvents) {
        when (event) {
            is MovieDetailEvents.GetMovie -> {
                getMovieFromCache(event.id)
            }

            is MovieDetailEvents.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is MovieDetailEvents.Error -> {
                if (event.uiComponent is UIComponent.None) {
                    logger.log("getMovies: ${(event.uiComponent as UIComponent.None).message}")
                } else {
                    appendToMessageQueue(event.uiComponent)
                }
            }
        }
    }

    private fun getMovieFromCache(id: Int) {
        getMovieDetail.execute(id).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }

                is DataState.Data -> {
                    state.value = state.value.copy(movie = dataState.data)
                }

                is DataState.Response -> {
                    // TODO(Handle errors)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            logger.log("Nothing to remove from DialogQueue")
        }
    }
}