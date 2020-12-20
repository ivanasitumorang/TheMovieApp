package com.azuka.themovieapp.presentation.entity


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Long,
    val releaseDate: String,
    val originalLanguage: String,
    val posterPath: String,
)