package com.example.movieverse_compose.data.api

import com.example.movieverse_compose.data.model.MoviesDTO
import com.example.movieverse_compose.data.model.TVShowDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TMDBApiInterface {

   @Headers("Authorization: Bearer ")
   @GET("movie/popular")
   suspend fun getPopularMovies(
       @Query("include_adult") includeAdult: Boolean = false,
       @Query("include_video") includeVideo: Boolean = false,
       @Query("language") language: String = "en-US",
       @Query("page") page: Int = 1,
       @Query("sort_by") sort_by: String = "popularity.desc"
   ): MoviesDTO

    @Headers("Authorization: Bearer ")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MoviesDTO

    @Headers("Authorization: Bearer ")
    @GET("discover/tv")
    suspend fun getTVShows(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sort_by: String = "popularity.desc"
    ): TVShowDTO

}