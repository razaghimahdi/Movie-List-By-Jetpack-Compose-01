package com.example.movie_datasource.network

import com.example.movie_domain.Movie
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface MovieService {

    suspend fun getMoviesList(
        page:Int,
        api_key:String
    ): List<Movie>

    companion object Factory {
        fun build(): MovieService {
            return MovieServiceImpl(
                httpClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                }

            )
        }
    }


}