package com.example.ui_moviedetail.ui.state

import com.example.core.constants.Queue
import com.example.core.domain_core.ProgressBarState
import com.example.core.domain_core.UIComponent
import com.example.movie_domain.Movie

data class MovieDetailState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movie: Movie? = null,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
