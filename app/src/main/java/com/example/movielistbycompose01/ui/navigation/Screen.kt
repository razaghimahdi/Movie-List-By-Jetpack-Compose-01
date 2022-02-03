package com.example.movielistbycompose01.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>){

    object MovieList: Screen(
        route = "movieList",
        arguments = emptyList()
    )

    object MovieDetail: Screen(
        route = "movieDetail",
        arguments = listOf(navArgument("MovieId") {
            type = NavType.IntType
        })
    )
}