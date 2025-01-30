package com.example.movieverse_compose.presentation


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieverse_compose.common.ViewState
import com.example.movieverse_compose.domain.model.MoviesModel
import com.example.movieverse_compose.domain.user_cases.GetPopularMoviesListUseCase
import com.example.movieverse_compose.domain.user_cases.GetTVShowsListUseCase
import com.example.movieverse_compose.domain.user_cases.GetUpcomingMoviesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainScreenViewModel (
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase,
    private val getUpcomingMoviesListUseCase: GetUpcomingMoviesListUseCase,
    private val getTVShowsListUseCase: GetTVShowsListUseCase,
    ): ViewModel() {

    private val _popularMovies = MutableStateFlow(MoviesViewState())
    val popularMovies: MutableStateFlow<MoviesViewState> = _popularMovies

    private val _upcomingMovies = MutableStateFlow(MoviesViewState())
    val upcomingMovies: MutableStateFlow<MoviesViewState> = _upcomingMovies

    private val _tvShows = MutableStateFlow(MoviesViewState())
    val tvShows: MutableStateFlow<MoviesViewState> = _tvShows

//    private var _selectedMovie: MutableLiveData<MoviesModel.Result> = MutableLiveData()
//    var selectedMovie: LiveData<MoviesModel.Result> = _selectedMovie
//
//
////    var selectedMovie by mutableStateOf(MoviesModel.Result())
//
//    fun setSelectedMovieValue(movie: MoviesModel.Result) {
//        _selectedMovie.value = movie
//    }

    private val _selectedMovie = MutableStateFlow(MoviesModel.Result())
    val selectedMovie = _selectedMovie.asStateFlow()

    fun setMovie(movie: MoviesModel.Result) {

        _selectedMovie.value = movie
        Log.d("fasdfsdfsdfsdsdfsdfaafs", "setMovie: ${_selectedMovie.value}")
    }

    init {
        getPopularMovies()
        getUpcomingMovies()
        getTVShows()
    }

    fun getPopularMovies() {
        getPopularMoviesListUseCase().onEach { viewState ->
            when (viewState) {
                is ViewState.Loading -> _popularMovies.value = MoviesViewState(isLoading = true)
                is ViewState.Success -> _popularMovies.value = viewState.data?.let { MoviesViewState(movies = it) } ?: MoviesViewState()
                is ViewState.Error -> _popularMovies.value = MoviesViewState(error = viewState.message)

            }
        }.launchIn(viewModelScope)
    }

    fun getUpcomingMovies() {
        getUpcomingMoviesListUseCase().onEach { viewState ->
            when (viewState) {
                is ViewState.Loading -> _upcomingMovies.value = MoviesViewState(isLoading = true)
                is ViewState.Success -> _upcomingMovies.value = viewState.data?.let { MoviesViewState(movies = it) } ?: MoviesViewState()
                is ViewState.Error -> _upcomingMovies.value = MoviesViewState(error = viewState.message)

            }
        }.launchIn(viewModelScope)
    }

    fun getTVShows() {
        getTVShowsListUseCase().onEach { viewState ->
            when (viewState) {
                is ViewState.Loading -> _tvShows.value = MoviesViewState(isLoading = true)
                is ViewState.Success -> _tvShows.value = viewState.data?.let { MoviesViewState(movies = it) } ?: MoviesViewState()
                is ViewState.Error -> _tvShows.value = MoviesViewState(error = viewState.message)

            }
        }.launchIn(viewModelScope)
    }

}
