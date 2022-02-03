package com.example.ui_movielist.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.constants.Logger
import com.example.core.constants.Queue
import com.example.core.domain_core.DataState
import com.example.core.domain_core.UIComponent
import com.example.movie_interactors.GetMovies
import com.example.ui_movielist.ui.state.MovieListEvents
import com.example.ui_movielist.ui.state.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject
constructor(
    private val getMoviesFromServer: GetMovies,
    private val logger: Logger,
): ViewModel(){


    val state: MutableState<MovieListState> = mutableStateOf(MovieListState())

    fun onTriggerEvent(event: MovieListEvents){
        when(event){
            is MovieListEvents.GetNextPage -> {
                getNextPage()
            }
            is MovieListEvents.GetMovies -> {
                getMovies()
            }
            is MovieListEvents.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is MovieListEvents.Error -> {
                if(event.uiComponent is UIComponent.None){
                    logger.log("getHeros: ${(event.uiComponent as UIComponent.None).message}")
                }
                else{
                    appendToMessageQueue(event.uiComponent)
                }
            }
        }
    }


    init {

        state.value = state.value.copy(api_key ="53387dc81a2da1494dd71b7168f2c455")

        onTriggerEvent(MovieListEvents.GetMovies)
    }

    private fun getNextPage(){

        state.value = state.value.copy(page = state.value.page + 1)

        getMoviesFromServer.execute(page = state.value.page, api_key = state.value.api_key).onEach { dataState ->
            when(dataState){
                is DataState.Response -> {
                    when(dataState.uiComponent){
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {

                    if (!dataState.data.isNullOrEmpty()){
                        val curr = ArrayList(state.value.movies)
                        curr.addAll(dataState.data?: listOf())
                        state.value = state.value.copy(movies = curr)
                    }else{
                        state.value = state.value.copy(isPageAvailable = false)
                    }


                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getMovies(){
        getMoviesFromServer.execute(page = state.value.page, api_key = state.value.api_key).onEach { dataState ->
            when(dataState){
                is DataState.Response -> {
                    when(dataState.uiComponent){
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(movies = dataState.data?: listOf())

                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(uiComponent: UIComponent){
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
        }catch (e: Exception){
            logger.log("Nothing to remove from DialogQueue")
        }
    }


}