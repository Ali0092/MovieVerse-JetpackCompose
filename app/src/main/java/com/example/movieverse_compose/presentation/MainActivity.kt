package com.example.movieverse_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.movieverse_compose.presentation.components.AppNavHost
import com.example.movieverse_compose.presentation.ui.MainScreen
import com.example.movieverse_compose.presentation.ui.MoviesDetailScreen
import com.example.movieverse_compose.ui.theme.MovieVerseComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieVerseComposeTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScr(){
    MovieVerseComposeTheme {
        MoviesDetailScreen()
    }
}