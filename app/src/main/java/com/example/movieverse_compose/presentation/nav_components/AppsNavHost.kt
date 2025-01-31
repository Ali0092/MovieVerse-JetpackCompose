package com.example.movieverse_compose.presentation.nav_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieverse_compose.presentation.screens.MainScreen
import com.example.movieverse_compose.presentation.screens.MoviesDetailScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route
) {

    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {

        composable(NavigationItem.Main.route) {
            MainScreen(navController = navController, viewModel = koinViewModel())
        }

        composable(route = NavigationItem.Details.route) {
            MoviesDetailScreen(navController = navController, viewModel = koinViewModel())
        }

    }
}