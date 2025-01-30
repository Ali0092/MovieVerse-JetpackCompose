package com.example.movieverse_compose.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieverse_compose.presentation.ui.MainScreen
import com.example.movieverse_compose.presentation.ui.MoviesDetailScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route
) {

    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {

        composable(NavigationItem.Main.route) {
            MainScreen(navController)
        }

        composable(NavigationItem.Details.route) {
            MoviesDetailScreen(navController)
        }
    }

}