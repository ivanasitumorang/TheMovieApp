package com.azuka.themovieapp.data.source.remote.response

import com.google.gson.annotations.SerializedName


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

data class MovieResponse(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Long,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("poster_path") val posterPath: String,
)