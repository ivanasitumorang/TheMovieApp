package com.azuka.themovieapp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

interface Repository {
    fun getMovies(): LiveData<List<Movie>>
    fun getTvSeries(): LiveData<List<TvSeries>>
    fun getMovieDetail(movieId: Long): LiveData<Movie>
    fun getTvSeriesDetail(tvSeriesId: Long): LiveData<TvSeries>
    fun getFavoriteMovies(): LiveData<PagedList<FavoriteGeneral>>
    fun getFavoriteTvSeries(): LiveData<PagedList<FavoriteGeneral>>
    fun insertFavoriteMovie(movie: Movie)
    fun insertFavoriteTvSeries(tvSeries: TvSeries)
    fun removeFavoriteMovie(movieId: Long)
    fun removeFavoriteTvSeries(tvSeriesId: Long)
    fun checkIfFavoriteMovie(movieId: Long): LiveData<Boolean>
    fun checkIfFavoriteTvSeries(tvSeriesId: Long): LiveData<Boolean>
}