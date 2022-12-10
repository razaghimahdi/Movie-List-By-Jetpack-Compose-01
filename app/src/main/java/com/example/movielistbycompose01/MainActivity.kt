package com.example.movielistbycompose01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import coil.ImageLoader
import com.example.movielistbycompose01.ui.navigation.Screen
import com.example.movielistbycompose01.ui.theme.BaseTheme
import com.example.ui_moviedetail.ui.MovieDetailScreen
import com.example.ui_moviedetail.ui.MovieDetailViewModel
import com.example.ui_movielist.ui.MovieListScreen
import com.example.ui_movielist.ui.MovieListViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader



    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                
                BoxWithConstraints {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.MovieList.route,
                        builder = {
                            addMovieList(
                                navController = navController,
                                imageLoader = imageLoader,
                                width = constraints.maxWidth / 2,
                            )
                            addMovieDetail(
                                imageLoader = imageLoader,
                                width = constraints.maxWidth / 2,
                            )
                        }
                    )
                }
            }
                
                
                
            }
        }



    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    fun NavGraphBuilder.addMovieList(
        navController: NavController,
        imageLoader: ImageLoader,
        width: Int,
    ) {
        composable(
            route = Screen.MovieList.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
        ){
            val viewModel: MovieListViewModel = hiltViewModel()
            MovieListScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToDetailScreen = { MovieId ->
                    Log.i("AppDebug", "addMovieList MovieId: "+MovieId)
                    Log.i("AppDebug", "addMovieList Screen.MovieDetail.route: "+Screen.MovieDetail.route)
                    navController.navigate("${Screen.MovieDetail.route}/$MovieId")
                },
                imageLoader = imageLoader,
            )
        }
    }

    @ExperimentalAnimationApi
    fun NavGraphBuilder.addMovieDetail(
        imageLoader: ImageLoader,
        width: Int,
    ) {
        composable(
            route = Screen.MovieDetail.route + "/{MovieId}",
            arguments = Screen.MovieDetail.arguments,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }
        ){
            val viewModel: MovieDetailViewModel = hiltViewModel()
            MovieDetailScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                imageLoader = imageLoader
            )
        }
    }



}
  
 