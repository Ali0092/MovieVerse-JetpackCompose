package com.example.movieverse_compose.domain.repository

import com.example.movieverse_compose.data.model.MoviesDTO
import com.example.movieverse_compose.data.model.TVShowDTO

interface MoviesRepository {

    suspend fun getPopularMovies(): MoviesDTO

    suspend fun getUpcomingMovies(): MoviesDTO

    suspend fun getTVShows(): TVShowDTO

}
