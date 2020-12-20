package com.azuka.themovieapp.utils.mapper

import com.azuka.themovieapp.BuildConfig
import com.azuka.themovieapp.data.source.remote.MovieResponse
import com.azuka.themovieapp.presentation.entity.Movie


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

object MovieDataMapper : Mapper<Movie, MovieResponse>() {
    override fun mapResponseToDomain(response: MovieResponse): Movie = with(response) {
        Movie(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            voteCount = voteCount,
            releaseDate = releaseDate,
            originalLanguage = originalLanguage,
            posterPath = "${BuildConfig.TMDB_IMAGE_URL}/w500/${posterPath.removePrefix("/")}"
        )
    }
}