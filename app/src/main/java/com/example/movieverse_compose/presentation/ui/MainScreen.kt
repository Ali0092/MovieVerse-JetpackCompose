package com.example.movieverse_compose.presentation.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
            Modifier.padding(start = 16.sdp), text = "MovieVerse", textSize = 18, isTextBold = true
        )
        OwlCarousal(
            isLoadingData = popularMoviesState.isLoading,
            popularMoviesList = popularMoviesState.movies.results.takeIf { it.isNotEmpty() }
                ?.subList(0, 6) ?: emptyList()
        ) { selectedMovie ->
            viewModel.setMovie(selectedMovie)
            navController.navigate(NavigationItem.Details.route)
        }

        //Upcoming Movies Row
        TextView(
            Modifier.padding(start = 16.sdp), text = "Upcomings", textSize = 14, isTextBold = true
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
            text = "TV Shows",
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
