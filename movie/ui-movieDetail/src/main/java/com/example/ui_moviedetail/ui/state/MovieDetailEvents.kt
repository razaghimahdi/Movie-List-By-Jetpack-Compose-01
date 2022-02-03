package com.example.ui_moviedetail.ui.state

import com.example.core.domain_core.UIComponent


sealed class MovieDetailEvents {

    data class GetMovie(
        val id: Int,
    ) : MovieDetailEvents()

    object OnRemoveHeadFromQueue: MovieDetailEvents()

    data class Error(
        val uiComponent: UIComponent
    ): MovieDetailEvents()
}