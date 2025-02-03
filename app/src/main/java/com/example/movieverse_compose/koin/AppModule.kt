package com.example.movieverse_compose.koin

import com.example.movieverse_compose.data.api.TMDBApiInterface
import com.example.movieverse_compose.data.repository.MoviesRepositoryImpl
import com.example.movieverse_compose.domain.repository.MoviesRepository
import com.example.movieverse_compose.domain.user_cases.GetPopularMoviesListUseCase
import com.example.movieverse_compose.domain.user_cases.GetTVShowsListUseCase
import com.example.movieverse_compose.domain.user_cases.GetUpcomingMoviesListUseCase
import com.example.movieverse_compose.presentation.viewmodel.MainScreenViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<TMDBApiInterface> {
        Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(TMDBApiInterface::class.java)
    }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
    factory { GetPopularMoviesListUseCase(get()) }
    factory { GetUpcomingMoviesListUseCase(get()) }
    factory { GetTVShowsListUseCase(get()) }
}

val viewModelModule = module {
    single { MainScreenViewModel(get(), get(), get()) }
}
