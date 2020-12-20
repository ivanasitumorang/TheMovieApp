package com.azuka.themovieapp.presentation.entity


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

data class TvSeries(
    val id: Long,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Long,
    val firstAirDate: String,
    val originalLanguage: String,
    val posterPath: String,
)