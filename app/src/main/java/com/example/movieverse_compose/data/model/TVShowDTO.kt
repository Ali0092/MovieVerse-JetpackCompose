package com.example.movieverse_compose.data.model


import androidx.annotation.Keep
import com.example.movieverse_compose.domain.model.MoviesModel
import com.google.gson.annotations.SerializedName

@Keep
data class TVShowDTO(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<Result> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
) {
    @Keep
    data class Result(
        @SerializedName("adult")
        val adult: Boolean = false,
        @SerializedName("backdrop_path")
        val backdropPath: String = "",
        @SerializedName("first_air_date")
        val firstAirDate: String = "",
        @SerializedName("genre_ids")
        val genreIds: List<Int> = emptyList(),
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("origin_country")
        val originCountry: List<String> = emptyList(),
        @SerializedName("original_language")
        val originalLanguage: String = "",
        @SerializedName("original_name")
        val originalName: String = "",
        @SerializedName("overview")
        val overview: String = "",
        @SerializedName("popularity")
        val popularity: Double = 0.0,
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        val voteCount: Int = 0
    )
}

fun TVShowDTO.Result.toMoviesModel(): MoviesModel.Result {
    return MoviesModel.Result(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalTitle = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.firstAirDate,
        title = this.name,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}