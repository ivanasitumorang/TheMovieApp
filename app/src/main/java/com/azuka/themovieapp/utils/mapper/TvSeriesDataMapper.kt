package com.azuka.themovieapp.utils.mapper

import com.azuka.themovieapp.BuildConfig
import com.azuka.themovieapp.data.source.remote.TvSeriesResponse
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

object TvSeriesDataMapper : Mapper<TvSeries, TvSeriesResponse>() {
    override fun mapResponseToDomain(response: TvSeriesResponse): TvSeries = with(response) {
        TvSeries(
            id = id,
            name = name,
            overview = overview,
            voteAverage = voteAverage,
            voteCount = voteCount,
            firstAirDate = firstAirDate,
            originalLanguage = originalLanguage,
            posterPath = "${BuildConfig.TMDB_IMAGE_URL}/w500/${posterPath.removePrefix("/")}"
        )
    }
}