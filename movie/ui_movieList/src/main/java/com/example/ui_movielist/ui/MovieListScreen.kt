package com.example.ui_movielist.ui

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.example.components.BaseScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.unit.dp
import com.example.components.LoadingShimmerMovieItem
import com.example.core.constants.Constants.PAGINATION_PAGE_SIZE
import com.example.core.domain_core.ProgressBarState
import com.example.ui_movielist.ui.component.MovieListToolbar
import com.example.ui_movielist.ui.state.MovieListEvents
import com.example.ui_movielist.ui.state.MovieListState


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MovieListScreen(
    state: MovieListState,
    events: (MovieListEvents) -> Unit,
    navigateToDetailScreen: (Int) -> Unit,
    imageLoader: ImageLoader
) {

    Log.i("AppDebug TAG", "MovieListScreen state.progressBarState: " + state.progressBarState)


    BaseScreen(
        queue = state.errorQueue,
        progressBarState = state.progressBarState, onRemoveHeadFromQueue = {
            events(MovieListEvents.OnRemoveHeadFromQueue)
        }) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
            ) {


                AnimatedVisibility(visible = state.progressBarState is ProgressBarState.Loading && state.movies.isEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(20) {
                            LoadingShimmerMovieItem(imageHeight = 220.dp)

                        }
                    }
                }

                AnimatedVisibility(visible = state.movies.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item() {
                            MovieListToolbar()
                        }

                        itemsIndexed(
                            items = state.movies
                        ) { index, movie ->
                            if (((index + 1) >= (state.page * PAGINATION_PAGE_SIZE)
                                        && state.progressBarState != ProgressBarState.Loading) && state.isPageAvailable
                            ) {
                                events(MovieListEvents.GetNextPage)
                            }

                            MovieListItem(
                                movie = movie,
                                onSelectHero = navigateToDetailScreen,
                                imageLoader = imageLoader,
                            )

                        }
                        /*
                        items(state.movies) { movie ->

                            MovieListItem(
                                movie = movie,
                                onSelectHero = navigateToDetailScreen,
                                imageLoader = imageLoader,
                            )


                        }
*/


                        items(20) {
                            AnimatedVisibility(visible = state.progressBarState is ProgressBarState.Loading) {
                                LoadingShimmerMovieItem(imageHeight = 220.dp)

                            }
                        }
                    }


                }
            }


            /*  if(state.progressBarState is ProgressBarState.Loading){
              }*/


        }

    }
}

