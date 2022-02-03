package com.example.movie_domain

data class Movie(
    val id: Int,
    val title: String,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int
)