package com.example.movieverse_compose.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieverse_compose.presentation.MainScreenViewModel
import com.example.movieverse_compose.presentation.components.MoviesCard
import com.example.movieverse_compose.presentation.components.OwlCarousal
import com.example.movieverse_compose.presentation.components.TextView
import com.example.movieverse_compose.ui.theme.backgroundColor
import com.example.movieverse_compose.utils.sdp
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {

    /*  viewModel.getPopularMovies()
    CoroutineScope(IO).launch {
        viewModel.popularMovies.collect{
            Log.d("CheckingPopularActivityLogs", "onCreate: ${it.movies}")
        }
    }*/

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

        //Popular Movies Row
        OwlCarousal()

        //Upcoming Movies Row
        TextView(
            Modifier.padding(start = 16.sdp, top = 8.sdp),
            text = "Upcomings",
            textSize = 14,
            isTextBold = true
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.sdp, top = 8.sdp)
        ) {
            items(5) {
                MoviesCard(
                    modifier = Modifier
                        .size(100.sdp, 130.sdp)
                        .padding(start = 8.sdp),
                    imageUrl = "R.drawable.temp1"
                )
            }
        }

        //TV Shows Row
        TextView(
            Modifier.padding(start = 16.sdp, top = 16.sdp),
            text = "TV Shows",
            textSize = 14,
            isTextBold = true
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.sdp, top = 8.sdp)
        ) {
            items(5) {
                MoviesCard(
                    modifier = Modifier
                        .size(100.sdp, 130.sdp)
                        .padding(start = 8.sdp),
                    imageUrl = "R.drawable.temp1"
                )
            }
        }

    }
}
