package com.example.movieverse_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.movieverse_compose.R
import com.example.movieverse_compose.ui.theme.MovieVerseComposeTheme
import com.example.movieverse_compose.ui.theme.backgroundColor
import com.example.movieverse_compose.ui.theme.textColor
import com.example.movieverse_compose.utils.sdp
import com.example.movieverse_compose.utils.textSdp

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState(pageCount = {
        4
    })
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
        HorizontalPager(state = pagerState) { page ->
            // Our page content
            MoviesCard(
                modifier = Modifier
                    .size(220.sdp, 280.sdp)
                    .padding(16.sdp),
                image = R.drawable.temp1
            )
        }

        //Upcoming Movies Row
        TextView(Modifier.padding(start = 16.sdp, top = 8.sdp), text = "Upcomings", textSize = 14, isTextBold = true)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.sdp, top = 8.sdp)
        ) {
            items(5){
                MoviesCard(
                    modifier = Modifier
                        .size(100.sdp, 130.sdp)
                        .padding(start = 8.sdp),
                    image = R.drawable.temp1
                )
            }
        }

        //TV Shows Row
        TextView(Modifier.padding(start = 16.sdp, top = 16.sdp), text = "TV Shows", textSize = 14, isTextBold = true)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.sdp, top = 8.sdp)
        ) {
            items(5){
                MoviesCard(
                    modifier = Modifier
                        .size(100.sdp, 130.sdp)
                        .padding(start = 8.sdp),
                    image = R.drawable.temp1
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
    modifier: Modifier = Modifier, image: Int = R.drawable.temp1
) {

    Card(
        modifier = modifier, shape = RoundedCornerShape(15.sdp)
    ) {
        Image(painterResource(image), contentDescription = null, contentScale = ContentScale.Crop)
    }

}

//This preview will ruin the system's performance... so lets not use this....
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MovieVerseComposeTheme {
//        Greetings()
//    }
//}