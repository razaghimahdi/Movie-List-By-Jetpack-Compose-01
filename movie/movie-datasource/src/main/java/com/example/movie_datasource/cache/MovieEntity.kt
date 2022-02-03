package com.example.movie_datasource.cache

import com.example.movie_domain.Movie
import com.example.moviedatasource.cache.Movie_Entity

fun Movie_Entity.toMovie():Movie{
    return Movie(
        id = id.toInt(),
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count.toInt(),
    )
}