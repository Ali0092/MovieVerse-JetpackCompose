package com.example.movieverse_compose.data.repository

import com.example.movieverse_compose.data.api.TMDBApiInterface
import com.example.movieverse_compose.data.model.MoviesDTO
import com.example.movieverse_compose.data.model.TVShowDTO
import com.example.movieverse_compose.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val tmdbApiInterface: TMDBApiInterface): MoviesRepository {

    override suspend fun getPopularMovies(): MoviesDTO {
       return tmdbApiInterface.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): MoviesDTO {
        return tmdbApiInterface.getUpcomingMovies()
    }

    override suspend fun getTVShows(): TVShowDTO {
        return tmdbApiInterface.getTVShows()
    }

}