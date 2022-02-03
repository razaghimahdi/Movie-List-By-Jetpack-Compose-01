package com.example.ui_movielist.ui.state

import com.example.core.domain_core.UIComponent

sealed class MovieListEvents{

    object GetMovies : MovieListEvents()

    object GetNextPage: MovieListEvents()

    object OnRemoveHeadFromQueue: MovieListEvents()

    data class Error(
        val uiComponent: UIComponent
    ): MovieListEvents()


}
