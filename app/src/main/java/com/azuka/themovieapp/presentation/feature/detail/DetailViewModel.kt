package com.azuka.themovieapp.presentation.feature.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 26/11/20.
 * Android Engineer
 */

class DetailViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private var movieId: Long = 0
    private var tvSeriesId: Long = 0

    fun setSelectedMovie(movieId: Long) {
        this.movieId = movieId
    }

    fun setSelectedTvSeriesId(tvSeriesId: Long) {
        this.tvSeriesId = tvSeriesId
    }

    fun getMovieDetail(): LiveData<Movie> = repository.getMovieDetail(movieId)

    fun getTvSeriesDetail(): LiveData<TvSeries> = repository.getTvSeriesDetail(tvSeriesId)

    fun addMovieToFavorite(movie: Movie) {
        repository.insertFavoriteMovie(movie)
    }

    fun addTvSeriesToFavorite(tvSeries: TvSeries) {
        repository.insertFavoriteTvSeries(tvSeries)
    }
}