package com.example.movieverse_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import com.example.movieverse_compose.ui.theme.MovieVerseComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ComposeView(this).apply {
            setContent {
                Text(text = "Hello Universe : )")
            }
        })
//        setContent {
//            MovieVerseComposeTheme {
//
//            }
//        }
    }
}
