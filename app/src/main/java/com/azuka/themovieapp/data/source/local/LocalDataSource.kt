package com.azuka.themovieapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.azuka.themovieapp.data.source.local.entity.MovieEntity
import com.azuka.themovieapp.data.source.local.entity.TvSeriesEntity
import com.azuka.themovieapp.data.source.local.room.FavoriteDao
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import javax.inject.Inject


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

class LocalDataSource @Inject constructor(private val dao: FavoriteDao) {
    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteGeneral> = dao.getMovies().map {
        with(it) {
            FavoriteGeneral(
                id = id,
                title = title,
                overview = overview,
                voteAverage = voteAverage,
                voteCount = voteCount,
                releaseDate = releaseDate,
                originalLanguage = originalLanguage,
                posterPath = posterPath
            )
        }
    }

    fun getTvSeries(): DataSource.Factory<Int, TvSeriesEntity> = dao.getTvSeries()
    fun insertFavoriteMovie(movieEntity: MovieEntity) = dao.insertMovie(movieEntity)
    fun insertFavoriteTvSeries(tvSeriesEntity: TvSeriesEntity) = dao.insertTvSeries(tvSeriesEntity)
    fun deleteFavoriteMovie(movieId: Long) = dao.deleteMovie(movieId)
    fun deleteFavoriteTvSeries(tvSeriesId: Long) = dao.deleteTvSeries(tvSeriesId)

    fun checkIfFavoriteMovie(movieId: Long): LiveData<List<MovieEntity>> =
        dao.checkIfFavoriteMovie(movieId)

    fun checkIfFavoriteTvSeries(tvSeriesId: Long): LiveData<List<TvSeriesEntity>> =
        dao.checkIfFavoriteTvSeries(tvSeriesId)
}