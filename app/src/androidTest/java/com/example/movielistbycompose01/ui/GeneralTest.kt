package com.example.movielistbycompose01.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import coil.ImageLoader
import com.example.movie_datasource.cache.MovieCache
import com.example.movie_datasource.network.MovieService
import com.example.movie_interactors.GetMovieDetail
import com.example.movie_interactors.GetMovies
import com.example.movie_interactors.MovieInteractors
import com.example.movielistbycompose01.FakeImageLoader
import com.example.movielistbycompose01.MainActivity
import com.example.movielistbycompose01.di.MovieInteractorsModule
import com.example.movielistbycompose01.movie_datasource_test.cache.MovieCacheFake
import com.example.movielistbycompose01.movie_datasource_test.cache.MovieDatabaseFake
import com.example.movielistbycompose01.movie_datasource_test.network.MovieServiceFake
import com.example.movielistbycompose01.movie_datasource_test.network.MovieServiceResponseType
import com.example.movielistbycompose01.ui.navigation.Screen
import com.example.movielistbycompose01.ui.theme.BaseTheme
import com.example.ui_moviedetail.ui.MovieDetailScreen
import com.example.ui_moviedetail.ui.MovieDetailViewModel
import com.example.ui_movielist.ui.MovieListScreen
import com.example.ui_movielist.ui.MovieListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import javax.inject.Singleton

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@UninstallModules(MovieInteractorsModule::class)
@HiltAndroidTest
class GeneralTest {


    @Module
    @InstallIn(SingletonComponent::class)
    object TestMovieInteractorsModule {

        @Provides
        @Singleton
        fun provideMovieCache(): MovieCache {
            return MovieCacheFake(MovieDatabaseFake())
        }

        @Provides
        @Singleton
        fun provideMovieService(): MovieService {
            return MovieServiceFake.build(
                type = MovieServiceResponseType.GoodData
            )
        }

        @Provides
        @Singleton
        fun provideMovieInteractors(
            cache: MovieCache,
            service: MovieService
        ): MovieInteractors {
            return MovieInteractors(
                getMovies = GetMovies(
                    cache = cache,
                    service = service,
                ), 
                getMovieDetail = GetMovieDetail(
                    cache = cache,
                ),
            )
        }
    }

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val imageLoader: ImageLoader = FakeImageLoader.build(context)


    @OptIn(ExperimentalFoundationApi::class)
    @Before
    fun before(){
        composeTestRule.setContent {
            BaseTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MovieList.route,
                    builder = {
                        composable(
                            route = Screen.MovieList.route,
                        ){
                            val viewModel: MovieListViewModel = hiltViewModel()
                            MovieListScreen(
                                state = viewModel.state.value,
                                events = viewModel::onTriggerEvent,
                                navigateToDetailScreen = { MovieId ->
                                    navController.navigate("${Screen.MovieDetail.route}/$MovieId")
                                },
                                imageLoader = imageLoader,
                            )
                        }
                        composable(
                            route = Screen.MovieDetail.route + "/{MovieId}",
                            arguments = Screen.MovieDetail.arguments,
                        ){
                            val viewModel: MovieDetailViewModel = hiltViewModel()
                            MovieDetailScreen(
                                state = viewModel.state.value,
                                events = viewModel::onTriggerEvent,
                                imageLoader = imageLoader
                            )
                        }
                    }
                )
            }
        }
    }


}

