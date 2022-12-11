package com.example.movielistbycompose01.movie_datasource_test.network

sealed class MovieServiceResponseType {

    object EmptyList : MovieServiceResponseType()

    object MalformedData : MovieServiceResponseType()

    object GoodData : MovieServiceResponseType()

    object Http404 : MovieServiceResponseType()


}
