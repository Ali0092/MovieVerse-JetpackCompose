package com.example.movieverse_compose.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieverse_compose.R
import com.example.movieverse_compose.common.sdp
import com.example.movieverse_compose.presentation.components.MoviesRow
import com.example.movieverse_compose.presentation.components.OwlCarousal
import com.example.movieverse_compose.presentation.components.TextView
import com.example.movieverse_compose.presentation.nav_components.NavigationItem
import com.example.movieverse_compose.presentation.viewmodel.MainScreenViewModel
import com.example.movieverse_compose.ui.theme.backgroundColor

@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(), viewModel: MainScreenViewModel
) {
    val popularMoviesState by viewModel.popularMovies.collectAsState()
    val upcomingMoviesState by viewModel.upcomingMovies.collectAsState()
    val tvShowsMoviesState by viewModel.tvShows.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = backgroundColor)
            .padding(top = 44.sdp, bottom = 12.sdp)
    ) {
        //Popular Movies Row
        TextView(
            Modifier.padding(start = 16.sdp),
            text = stringResource(R.string.home_title),
            textSize = 18,
            isTextBold = true
        )
        OwlCarousal(isLoadingData = popularMoviesState.isLoading,
            popularMoviesList = popularMoviesState.movies.results.takeIf { it.isNotEmpty() }
                ?.subList(0, 10) ?: emptyList()) { selectedMovie ->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

        //Upcoming Movies Row
        TextView(
            Modifier.padding(start = 16.sdp),
            text = stringResource(R.string.upcomings_title),
            textSize = 14,
            isTextBold = true
        )

        MoviesRow(isLoadingData = upcomingMoviesState.isLoading,
            moviesList = upcomingMoviesState.movies.results.takeIf { it.isNotEmpty() }
                ?: emptyList()) { selectedMovie ->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

        //TV Shows Row
        TextView(
            Modifier.padding(start = 16.sdp, top = 16.sdp),
            text = stringResource(R.string.tv_shows_title),
            textSize = 14,
            isTextBold = true
        )

        MoviesRow(isLoadingData = tvShowsMoviesState.isLoading,
            moviesList = tvShowsMoviesState.movies.results.takeIf { it.isNotEmpty() }
                ?: emptyList()) { selectedMovie ->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

    }
}
