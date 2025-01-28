package com.example.composetweaks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OwlCarousal() {
    val pagerState = rememberPagerState(Int.MAX_VALUE / 2) {
        Int.MAX_VALUE
    }

    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 48.dp),
        pageSpacing = 8.dp
    ) { page ->
        //defined in ClubbedPhotos.kt.
        PhotoItem(
            uri = listOfImages[page % listOfImages.size],
            modifier = Modifier.clip(RoundedCornerShape(8.dp)).carouselTransition(page, pagerState),
            imageHeight = 200.dp
        )
    }
}

@Composable
fun PhotoItem(
    modifier: Modifier = Modifier,
    uri: String,
    imageHeight: Dp = 150.dp,
    showOverLay: Boolean = false,
    num: Int = 0,
    onClick: () -> Unit = {},
    onMoreClicked: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.height(imageHeight)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(uri).crossfade(true).build(),
            contentDescription = uri,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize().clickable {
                if(showOverLay.not())
                    onClick()
            }
        )
        if (showOverLay) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable { onMoreClicked() }
            )
            Text(
                text = "+$num More",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.80f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }