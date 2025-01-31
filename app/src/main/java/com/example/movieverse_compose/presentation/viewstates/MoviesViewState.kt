package com.example.movieverse_compose.presentation.viewstates

import com.example.movieverse_compose.domain.model.MoviesModel

data class MoviesViewState(
    val isLoading: Boolean = false,
    val movies: MoviesModel = MoviesModel(),
    val error: String? = null
)
