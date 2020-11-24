package com.azuka.themovieapp.data

import com.google.gson.annotations.SerializedName


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Long,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val originalLanguage: String,
    @SerializedName("poster_path") val posterPath: String,
)