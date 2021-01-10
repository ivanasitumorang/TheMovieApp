package com.azuka.themovieapp.utils.mapper

import com.azuka.themovieapp.BuildConfig
import com.azuka.themovieapp.data.source.local.entity.TvSeriesEntity
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

object TvSeriesDataMapper : Mapper<TvSeriesEntity, TvSeries, TvSeriesResponse>() {
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

    override fun mapDomainToEntity(dto: TvSeries): TvSeriesEntity = with(dto) {
        TvSeriesEntity(
            id, name, overview, voteAverage, voteCount, firstAirDate, originalLanguage, posterPath
        )
    }

    override fun mapResponseToEntity(response: TvSeriesResponse): TvSeriesEntity = with(response) {
        TvSeriesEntity(
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

    override fun mapEntityToDomain(entity: TvSeriesEntity): TvSeries = with(entity) {
        TvSeries(
            id, name, overview, voteAverage, voteCount, firstAirDate, originalLanguage, posterPath
        )
    }
}