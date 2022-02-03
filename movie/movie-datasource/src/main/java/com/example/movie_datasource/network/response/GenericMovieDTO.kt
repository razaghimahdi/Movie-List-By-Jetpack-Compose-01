package com.example.movie_datasource.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenericMovieDTO (

    @SerialName("page") val page : Int,
    @SerialName("results") val results : List<MovieDTO>,
    @SerialName("total_pages") val total_pages : Int,
    @SerialName("total_results") val total_results : Int
)