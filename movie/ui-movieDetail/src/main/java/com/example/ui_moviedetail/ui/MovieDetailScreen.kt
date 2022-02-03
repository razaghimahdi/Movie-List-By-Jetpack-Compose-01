package com.example.ui_moviedetail.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.example.components.BaseScreen
import com.example.ui_moviedetail.ui.component.BodyDetail
import com.example.ui_moviedetail.ui.component.MovieDetailToolbar
import com.example.ui_moviedetail.ui.state.MovieDetailEvents
import com.example.ui_moviedetail.ui.state.MovieDetailState


@ExperimentalAnimationApi
@Composable
fun MovieDetailScreen(
    state: MovieDetailState,
    events: (MovieDetailEvents) -> Unit,
    imageLoader: ImageLoader,
) {
    BaseScreen(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = {
            events(MovieDetailEvents.OnRemoveHeadFromQueue)
        },
        progressBarState = state.progressBarState,
    ) {

        state.movie?.let { Movie ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {


                item {
                    MovieDetailToolbar()
                }



                item {
                    BodyDetail(movie = state.movie, imageLoader = imageLoader)
                }



            }


        }


    }
}
