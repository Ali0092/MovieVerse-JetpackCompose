package com.example.movieverse_compose.domain.user_cases

import android.util.Log
import com.example.movieverse_compose.common.ViewState
import com.example.movieverse_compose.data.model.toMoviesModel
import com.example.movieverse_compose.domain.model.MoviesModel
import com.example.movieverse_compose.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularMoviesListUseCase(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(): Flow<ViewState<MoviesModel>> = flow {
        try {
            Log.d("CheckingPopularActivityLogs", "trying in useCase....")

            emit(ViewState.Loading())
            val response = moviesRepository.getPopularMovies()
            val responseModel =
                if (response.results.isEmpty()) emptyList<MoviesModel.Result>() else response.results.map { it.toMoviesModel() }
            val finalResponseModel = MoviesModel(page = response.page, results = responseModel)

            emit(ViewState.Success(finalResponseModel))
        } catch (e: Exception) {
            emit(ViewState.Error(message = e.message))
        }

    }

}
