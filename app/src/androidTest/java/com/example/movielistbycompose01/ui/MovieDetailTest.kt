package com.example.movielistbycompose01.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import com.example.movielistbycompose01.FakeImageLoader
import com.example.movielistbycompose01.movie_datasource_test.network.data.MovieDataValid
import com.example.movielistbycompose01.movie_datasource_test.network.serializeMovieData
import com.example.movielistbycompose01.ui.theme.BaseTheme
import com.example.ui_moviedetail.ui.MovieDetailScreen
import com.example.ui_moviedetail.ui.state.MovieDetailState
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalAnimationApi
class MovieDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val imageLoader: ImageLoader = FakeImageLoader.build(context)
    private val movieData = serializeMovieData(MovieDataValid.data)

    @Test
    fun isMovieDataShown() {
        // lets select random movie
        val movie = movieData[Random.nextInt(0, movieData.size)]

        composeTestRule.setContent {
            BaseTheme {
                val state = remember {
                    MovieDetailState(movie = movie)
                }
                MovieDetailScreen(state = state, events = {}, imageLoader = imageLoader)
            }
        }


        composeTestRule.onNodeWithText(movie.title)
        composeTestRule.onNodeWithText(movie.original_title)
        composeTestRule.onNodeWithText(movie.release_date).assertIsDisplayed()
        composeTestRule.onNodeWithText(movie.vote_count.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(movie.vote_average.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(movie.original_language).assertIsDisplayed()
        composeTestRule.onNodeWithText(movie.overview).assertIsDisplayed()



    }


}