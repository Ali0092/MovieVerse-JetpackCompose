package com.example.movieverse_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.movieverse_compose.presentation.ui.MainScreen
import com.example.movieverse_compose.ui.theme.MovieVerseComposeTheme

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
