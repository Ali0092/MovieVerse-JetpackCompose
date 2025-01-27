package com.example.movieverse_compose.hilt

import com.example.movieverse_compose.data.api.TMDBApiInterface
import com.example.movieverse_compose.data.repository.MoviesRepositoryImpl
import com.example.movieverse_compose.domain.repository.MoviesRepository
import com.example.movieverse_compose.domain.user_cases.GetPopularMoviesListUseCase
import com.example.movieverse_compose.domain.user_cases.GetTVShowsListUseCase
import com.example.movieverse_compose.domain.user_cases.GetUpcomingMoviesListUseCase
import com.example.movieverse_compose.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideTMDBApiInterface(): TMDBApiInterface =
    Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(TMDBApiInterface::class.java)

fun provideMainRepository(tmdbApiInterface: TMDBApiInterface): MoviesRepository {
    return MoviesRepositoryImpl(tmdbApiInterface)
}

fun provideMoviesListUseCase(repository: MoviesRepository): GetPopularMoviesListUseCase {
    return GetPopularMoviesListUseCase(repository)
}

fun provideUpcomingMoviesListUseCase(repository: MoviesRepository): GetUpcomingMoviesListUseCase {
    return GetUpcomingMoviesListUseCase(repository)
}

fun provideTvShowsUseCase(repository: MoviesRepository): GetTVShowsListUseCase {
    return GetTVShowsListUseCase(repository)
}

val appModule = module {
    single { provideTMDBApiInterface() }
    single { provideMainRepository(get()) }
    single { provideMoviesListUseCase(get()) }
    single { provideUpcomingMoviesListUseCase(get()) }
    single { provideTvShowsUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainScreenViewModel(get(), get(), get()) }
}

/*
import com.example.movieverse_compose.data.api.TMDBApiInterface
import com.example.movieverse_compose.data.repository.MoviesRepositoryImpl
import com.example.movieverse_compose.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getTMDBApiInterface(): TMDBApiInterface =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApiInterface::class.java)

    @Provides
    @Singleton
    fun getMainRepository(tmdbApiInterface: TMDBApiInterface): MoviesRepository {
        return MoviesRepositoryImpl(tmdbApiInterface)
    }

}*/
