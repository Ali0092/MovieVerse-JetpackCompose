package com.example.movieverse_compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.movieverse_compose.ui.theme.textColor
import com.example.movieverse_compose.common.sdp
import com.example.movieverse_compose.common.textSdp
import com.example.movieverse_compose.domain.model.MoviesModel

@Composable
fun TextView(
    modifier: Modifier = Modifier,
    text: String = "",
    textSize: Int = 12,
    isTextBold: Boolean = false,
    tColor: Color = textColor
) {
    Text(
        modifier = modifier,
        text = text,
        color = tColor,
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
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MoviesRow(
    isLoadingData: Boolean = false,
    moviesList: List<MoviesModel.Result> = listOf()
){

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.sdp, top = 8.sdp)
    ) {
        if (isLoadingData){
            items(3){
                MoviesCard(
                    modifier = Modifier
                        .size(100.sdp, 130.sdp)
                        .padding(start = 8.sdp),
                    imageUrl =""
                )
            }
        }else{
            items(moviesList) { movies->
                MoviesCard(
                    modifier = Modifier
                        .size(100.sdp, 130.sdp)
                        .padding(start = 8.sdp),
                    imageUrl = "https://image.tmdb.org/t/p/w500/${movies.posterPath}"
                )
            }
        }

    }

}