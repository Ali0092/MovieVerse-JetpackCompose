package com.example.movieverse_compose.presentation.components



enum class Screen {
    MAIN,
    DETAIL,
}
sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(Screen.MAIN.name)
    object Details : NavigationItem(Screen.DETAIL.name)
}