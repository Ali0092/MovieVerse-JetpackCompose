package com.example.movieverse_compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.example.movieverse_compose.ui.theme.textColor
import com.example.movieverse_compose.utils.sdp
import com.example.movieverse_compose.utils.textSdp

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
