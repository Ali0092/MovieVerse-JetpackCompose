package com.example.movieverse_compose.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.movieverse_compose.ui.theme.MovieVerseComposeTheme
import com.example.movieverse_compose.ui.theme.backgroundColor
import com.example.movieverse_compose.ui.theme.textColor
import com.example.movieverse_compose.utils.sdp
import com.example.movieverse_compose.utils.textSdp
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            MovieVerseComposeTheme {
                MainScreen()
            }
        }

    }
}

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
            Modifier.padding(start = 16.sdp),
            text = "MovieVerse",
            textSize = 18,
            isTextBold = true
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

@Composable
fun TextView(
    modifier: Modifier = Modifier,
    text: String = "",
    textSize: Int = 12,
    isTextBold: Boolean = false,
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        fontSize = textSize.textSdp,
        fontWeight = if (isTextBold) FontWeight.Bold else FontWeight.Normal
    )
}

@Composable
fun MoviesCard(
    modifier: Modifier = Modifier, imageUrl: String = ""
) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(15.sdp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}
