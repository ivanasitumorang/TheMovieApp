package com.azuka.themovieapp.data.source

import com.azuka.themovieapp.data.source.remote.RemoteDataSource
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.utils.mapper.MovieDataMapper
import com.azuka.themovieapp.utils.mapper.TvSeriesDataMapper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteSource: RemoteDataSource) : Repository {
    override fun getMovies(): List<Movie> = MovieDataMapper.mapResponsesToDomains(
        remoteSource.getMovies()
    )

    override fun getTvSeries(): List<TvSeries> = TvSeriesDataMapper.mapResponsesToDomains(
        remoteSource.getTvSeries()
    )
}