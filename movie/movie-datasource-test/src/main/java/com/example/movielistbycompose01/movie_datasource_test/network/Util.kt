package com.example.movielistbycompose01.movie_datasource_test.network
 
import com.example.movie_datasource.network.response.GenericMovieDTO
import com.example.movie_datasource.network.response.MovieDTO
import com.example.movie_datasource.network.response.toMovie
import com.example.movie_domain.Movie
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
}

fun serializeMovieData(jsonData: String): List<Movie>{
    val movies: List<Movie> = json.decodeFromString<GenericMovieDTO>(jsonData).results.map { it.toMovie() }
    return movies
}