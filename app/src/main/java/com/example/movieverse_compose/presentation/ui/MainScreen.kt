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
import androidx.compose.ui.Modifier
import com.example.movieverse_compose.common.sdp
import com.example.movieverse_compose.presentation.MainScreenViewModel
import com.example.movieverse_compose.presentation.components.MoviesRow
import com.example.movieverse_compose.presentation.components.OwlCarousal
import com.example.movieverse_compose.presentation.components.TextView
import com.example.movieverse_compose.ui.theme.backgroundColor
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
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
        //Top Movies Row
        TextView(
            Modifier.padding(start = 16.sdp), text = "MovieVerse", textSize = 18, isTextBold = true
        )

        if (popularMoviesState.isLoading) {
            Log.d(
                "popularMoviesStateLogs",
                "popularMoviesState: loading movies ${popularMoviesState.error}"
            )
        } else if (popularMoviesState.error?.isNotEmpty() == true) {
            Log.d("popularMoviesStateLogs", "popularMoviesState: its an error ${popularMoviesState.error}")
        } else {
            if (popularMoviesState.movies.results.isNotEmpty()) {
                Log.d(
                    "popularMoviesStateLogs",
                    "popularMoviesState: movies are not empty ${popularMoviesState.movies.results}"
                )
                //Popular Movies Row
                OwlCarousal(popularMoviesList = popularMoviesState.movies.results)
            } else {
                Log.d("popularMoviesStateLogs", "popularMoviesState: movies are empty ")
            }
        }

        //Upcoming Movies Row
        TextView(
            Modifier.padding(start = 16.sdp, top = 8.sdp),
            text = "Upcomings",
            textSize = 14,
            isTextBold = true
        )

        if (upcomingMoviesState.isLoading) {
            Log.d(
                "popularMoviesStateLogs",
                "upcomingMoviesState: loading movies ${upcomingMoviesState.error}"
            )
        } else if (upcomingMoviesState.error?.isNotEmpty() == true) {
            Log.d("popularMoviesStateLogs", "upcomingMoviesState: its an error ${upcomingMoviesState.error}")
        } else {
            if (upcomingMoviesState.movies.results.isNotEmpty()) {
                Log.d(
                    "popularMoviesStateLogs",
                    "upcomingMoviesState: movies are not empty ${upcomingMoviesState.movies.results}"
                )
                //Popular Movies Row
                MoviesRow(moviesList = upcomingMoviesState.movies.results)
            } else {
                Log.d("popularMoviesStateLogs", "upcomingMoviesState: movies are empty ")
            }
        }

        //TV Shows Row
        TextView(
            Modifier.padding(start = 16.sdp, top = 16.sdp),
            text = "TV Shows",
            textSize = 14,
            isTextBold = true
        )

        if (tvShowsMoviesState.isLoading) {
            Log.d(
                "popularMoviesStateLogs",
                "tvShowsMoviesState: loading movies ${tvShowsMoviesState.error}"
            )
        } else if (tvShowsMoviesState.error?.isNotEmpty() == true) {
            Log.d("popularMoviesStateLogs", "tvShowsMoviesState: its an error ${tvShowsMoviesState.error}")
        } else {
            if (tvShowsMoviesState.movies.results.isNotEmpty()) {
                Log.d(
                    "popularMoviesStateLogs",
                    "tvShowsMoviesState: movies are not empty ${tvShowsMoviesState.movies.results}"
                )
                //Popular Movies Row
                MoviesRow(moviesList = tvShowsMoviesState.movies.results)
            } else {
                Log.d("popularMoviesStateLogs", "tvShowsMoviesState: movies are empty ")
            }
        }

    }
}
