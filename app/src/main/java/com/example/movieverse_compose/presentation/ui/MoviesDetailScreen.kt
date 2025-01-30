package com.example.movieverse_compose.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieverse_compose.R
import com.example.movieverse_compose.common.sdp
import com.example.movieverse_compose.presentation.MainScreenViewModel
import com.example.movieverse_compose.presentation.components.MoviesCard
import com.example.movieverse_compose.presentation.components.TextView
import com.example.movieverse_compose.ui.theme.backgroundColor
import com.example.movieverse_compose.ui.theme.greyColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesDetailScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {



    Column(
        modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(color = backgroundColor)
        .padding(top = 44.sdp, bottom = 12.sdp, start = 12.sdp, end = 12.sdp)

    ) {
        //top row....
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(36.sdp),
                backgroundColor = greyColor,
                shape = RoundedCornerShape(8.sdp)
            ) {
                Image(painter = painterResource(R.drawable.arrow_back_icon), contentDescription = "", modifier = Modifier.fillMaxSize().padding(6.sdp))
            }
            Row(
                modifier = Modifier.fillMaxWidth().offset(x = -24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextView( text = "Movie Details", textSize = 14, isTextBold = true)
            }

        }

        //Movie Image
        MoviesCard(modifier = Modifier.fillMaxWidth().height(320.sdp).padding(top = 12.sdp))

        TextView(modifier = Modifier.fillMaxWidth().padding(top = 16.sdp), text = "Aladin", textSize = 21, isTextBold = true)

        TextView(modifier = Modifier.fillMaxWidth().padding(top = 8.sdp), text = "To fetch documents from Firestore where the document IDs match the values in editorsInoc: List<String>, and keep a real-time snapshot listener, you need to use multiple snapshot listeners—one for each document in the list.", textSize = 14, isTextBold = false)



    }

}