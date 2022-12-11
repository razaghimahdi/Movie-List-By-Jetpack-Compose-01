package com.example.movielistbycompose01.movie_datasource_test.network

import com.example.movie_datasource.network.MovieService
import com.example.movie_datasource.network.MovieServiceImpl
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataEmpty
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataMalformed
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataValid
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*

class MovieServiceFake {

    companion object Factory {

        private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
        private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

        fun build(type: MovieServiceResponseType): MovieService {
            val client = HttpClient(MockEngine) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                        }
                    )
                }
                engine {
                    addHandler { request ->
                        when (request.url.fullUrl) {
                            "https://api.themoviedb.org/3/movie/popular?api_key=53387dc81a2da1494dd71b7168f2c455&page=1" -> {
                                val responseHeaders = headersOf(
                                    "Content-Type" to listOf("application/json", "charset=utf-8")
                                )
                                when (type) {
                                    is MovieServiceResponseType.EmptyList -> {
                                        respond(
                                            MovieDataEmpty.data,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is MovieServiceResponseType.MalformedData -> {
                                        respond(
                                            MovieDataMalformed.data,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is MovieServiceResponseType.GoodData -> {
                                        respond(
                                            MovieDataValid.data,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is MovieServiceResponseType.Http404 -> {
                                        respond(
                                            MovieDataEmpty.data,
                                            status = HttpStatusCode.NotFound,
                                            headers = responseHeaders
                                        )
                                    }
                                }
                            }
                            else -> error("Unhandled ${request.url.fullUrl}")
                        }
                    }
                }
            }

            return MovieServiceImpl(client)
        }


    }


}