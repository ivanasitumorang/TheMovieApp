package com.azuka.themovieapp.data.source.remote

import com.google.gson.annotations.SerializedName


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

data class TvSeriesResponse(
    val id: Long,
    val name: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Long,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("poster_path") val posterPath: String,
)