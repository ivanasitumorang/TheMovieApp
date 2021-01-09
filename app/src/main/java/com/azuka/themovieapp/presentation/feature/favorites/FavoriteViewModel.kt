package com.azuka.themovieapp.presentation.feature.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.source.Repository
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

    fun getMovieList(): LiveData<List<Movie>> = repository.getFavoriteMovies()

    fun getTvSeries(): LiveData<List<TvSeries>> = repository.getFavoriteTvShow()

    fun checkIfFavoriteMovie(id: Long): LiveData<Boolean> {
        return Transformations.switchMap(repository.getFavoriteMovies()) { movieList ->
            val isFavorite = MutableLiveData<Boolean>()
            val isExist = movieList.firstOrNull {
                it.id == id
            }
            isFavorite.value = isExist != null
            isFavorite
        }
    }

    fun checkIfFavoriteTvSeries(id: Long): LiveData<Boolean> {
        return Transformations.switchMap(repository.getFavoriteTvShow()) { tvSeriesList ->
            val isFavorite = MutableLiveData<Boolean>()
            val isExist = tvSeriesList.firstOrNull {
                it.id == id
            }
            isFavorite.value = isExist != null
            isFavorite
        }
    }
}