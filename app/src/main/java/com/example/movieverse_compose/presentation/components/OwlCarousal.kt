package com.example.movieverse_compose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.movieverse_compose.common.sdp
import com.example.movieverse_compose.domain.model.MoviesModel
import kotlin.math.absoluteValue

@Composable
fun OwlCarousal(
    isLoadingData: Boolean = false,
    popularMoviesList: List<MoviesModel.Result> = emptyList(),
    onClicked: (MoviesModel.Result)-> Unit
) {
    val pagerState =
        rememberPagerState(pageCount = { if (!isLoadingData) popularMoviesList.size else 3 })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.sdp, bottom = 16.sdp)
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 48.dp),
            pageSpacing = 8.dp
        ) { page ->
            //defined in ClubbedPhotos.kt.
            MoviesCard(
                modifier = Modifier
                    .clickable {
                        onClicked(popularMoviesList[page])
                    }
                    .height(280.sdp)
                    .width(250.sdp)
                    .clip(RoundedCornerShape(8.dp))
                    .carouselTransition(page, pagerState),
                imageUrl = if (isLoadingData) "" else "https://image.tmdb.org/t/p/w500/${popularMoviesList[page].posterPath}"
            )
        }
        Spacer(modifier = Modifier.padding(8.sdp))
        DotIndicator(popularMoviesList.size, pagerState.currentPage)
    }

}

fun Modifier.carouselTransition(page: Int, pagerState: PagerState) = graphicsLayer {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    val transformation = lerp(
        start = 0.80f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    alpha = transformation
    scaleY = transformation
}

@Composable
fun DotIndicator(
    pageCount: Int, currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == currentPage) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) Color.White else Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}