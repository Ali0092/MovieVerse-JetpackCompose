package com.example.movieverse_compose.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieverse_compose.common.sdp
import com.example.movieverse_compose.domain.model.MoviesModel
import com.example.movieverse_compose.presentation.MainScreenViewModel
import com.example.movieverse_compose.presentation.components.MoviesRow
import com.example.movieverse_compose.presentation.components.NavigationItem
import com.example.movieverse_compose.presentation.components.OwlCarousal
import com.example.movieverse_compose.presentation.components.TextView
import com.example.movieverse_compose.ui.theme.backgroundColor
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainScreenViewModel
) {
    val popularMoviesState by viewModel.popularMovies.collectAsState()
    val upcomingMoviesState by viewModel.upcomingMovies.collectAsState()
    val tvShowsMoviesState by viewModel.tvShows.collectAsState()

    var isPopularMoviesLoading by remember { mutableStateOf(true) }
    var popularMoviesList by remember { mutableStateOf(listOf<MoviesModel.Result>()) }

    var isUpcomingMoviesLoading by remember { mutableStateOf(true) }
    var upcomingMoviesList by remember { mutableStateOf(listOf<MoviesModel.Result>()) }

    var isTvShowsLoading by remember { mutableStateOf(true) }
    var tvShowsList by remember { mutableStateOf(listOf<MoviesModel.Result>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = backgroundColor)
            .padding(top = 44.sdp, bottom = 12.sdp)
    ) {
        //Top Movies Row
        TextView(
            Modifier.padding(start = 16.sdp), text = "MovieVerse", textSize = 18, isTextBold = true
        )

        OwlCarousal(isLoadingData = isPopularMoviesLoading, popularMoviesList = popularMoviesList){ selectedMovie->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

        if (popularMoviesState.isLoading) {
            //its loading
            isPopularMoviesLoading = true
        } else if (popularMoviesState.error?.isNotEmpty() == true) {
            //its an error
            isPopularMoviesLoading = true
        } else {
            if (popularMoviesState.movies.results.isNotEmpty()) {
                //Popular Movies Row
                isPopularMoviesLoading = false
                popularMoviesList = popularMoviesState.movies.results.subList(0,6)
            } else {
                isPopularMoviesLoading = true
            }
        }

        //Upcoming Movies Row
        TextView(
            Modifier.padding(start = 16.sdp ),
            text = "Upcomings",
            textSize = 14,
            isTextBold = true
        )

        MoviesRow(isLoadingData = isUpcomingMoviesLoading, moviesList = upcomingMoviesList){ selectedMovie->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

        if (upcomingMoviesState.isLoading) {
            isUpcomingMoviesLoading = true
        } else if (upcomingMoviesState.error?.isNotEmpty() == true) {
            isUpcomingMoviesLoading = true
        } else {
            if (upcomingMoviesState.movies.results.isNotEmpty()) {
               isUpcomingMoviesLoading = false
               upcomingMoviesList = upcomingMoviesState.movies.results.subList(0,6)
            } else {
                isUpcomingMoviesLoading = true
            }
        }

        //TV Shows Row
        TextView(
            Modifier.padding(start = 16.sdp, top = 16.sdp),
            text = "TV Shows",
            textSize = 14,
            isTextBold = true
        )

        MoviesRow(isLoadingData = isTvShowsLoading, moviesList = tvShowsList){selectedMovie->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

        if (tvShowsMoviesState.isLoading) {
            isTvShowsLoading = true
        } else if (tvShowsMoviesState.error?.isNotEmpty() == true) {
            isTvShowsLoading = true
        } else {
            if (tvShowsMoviesState.movies.results.isNotEmpty()) {
                isTvShowsLoading = false
                tvShowsList = tvShowsMoviesState.movies.results.subList(0,6)
            } else {
                isTvShowsLoading = true
            }
        }

    }
}
