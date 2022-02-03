package com.example.movie_datasource.network

import com.example.core.constants.Constants
import com.example.movie_datasource.network.response.GenericMovieDTO
import com.example.movie_datasource.network.response.toMovie
import com.example.movie_domain.Movie
import io.ktor.client.*
import io.ktor.client.request.*

class MovieServiceImpl(private val httpClient: HttpClient) : MovieService {


    override suspend fun getMoviesList(page: Int, api_key: String): List<Movie> {

        return httpClient.get<GenericMovieDTO> {
            url("${Constants.MOVIES_POPULAR}?page=$page&api_key=$api_key")
        }.results.map {
            it.toMovie()
        }
    }


}