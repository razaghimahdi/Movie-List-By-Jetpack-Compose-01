package com.example.movie_datasource.network.response

import com.example.movie_domain.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    @SerialName("id") val id: Int,
    @SerialName("backdrop_path") val backdrop_path: String,
    @SerialName("original_language") val original_language: String,
    @SerialName("original_title") val original_title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val poster_path: String,
    @SerialName("release_date") val release_date: String,
    @SerialName("title") val title: String,
    @SerialName("vote_average") val vote_average: Double,
    @SerialName("vote_count") val vote_count: Int
)

fun MovieDTO.toMovie(): Movie {
    return Movie(
        id = id,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count,
    )
}

