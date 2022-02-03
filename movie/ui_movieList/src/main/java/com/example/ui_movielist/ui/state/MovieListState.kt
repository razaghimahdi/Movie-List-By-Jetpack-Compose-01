package com.example.ui_movielist.ui.state

import com.example.core.constants.Queue
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent
import com.example.movie_domain.Movie

data class MovieListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movies: List<Movie> = listOf(),
    val api_key: String = "",
    val page: Int = 1,
    val isPageAvailable: Boolean = true,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
