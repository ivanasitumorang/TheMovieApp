package com.azuka.themovieapp.presentation.feature.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

class FavoriteViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    fun addMovieToFavorite(movie: Movie) {
        repository.insertFavoriteMovie(movie)
    }

    fun addTvSeriesToFavorite(tvSeries: TvSeries) {
        repository.insertFavoriteTvSeries(tvSeries)
    }

    fun removeMovieFromFavorite(id: Long) {
        repository.removeFavoriteMovie(id)
    }

    fun removeTvSeriesFromFavorite(id: Long) {
        repository.removeFavoriteTvSeries(id)
    }

    fun getMovieList(): LiveData<PagedList<FavoriteGeneral>> = repository.getFavoriteMovies()

    fun getTvSeries(): LiveData<PagedList<FavoriteGeneral>> = repository.getFavoriteTvSeries()

    fun checkIfFavoriteMovie(id: Long): LiveData<Boolean> = repository.checkIfFavoriteMovie(id)

    fun checkIfFavoriteTvSeries(id: Long): LiveData<Boolean> =
        repository.checkIfFavoriteTvSeries(id)
}