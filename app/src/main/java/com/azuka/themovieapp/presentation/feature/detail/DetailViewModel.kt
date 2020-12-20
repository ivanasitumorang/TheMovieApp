package com.azuka.themovieapp.presentation.feature.detail

import androidx.hilt.lifecycle.ViewModelInject
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

    private val movieList = repository.getMovies()
    private val tvSeriesList = repository.getTvSeries()

    fun getTvSeriesById(tvSeriesId: Long): TvSeries? {
        return tvSeriesList.find { tvSeries ->
            tvSeries.id == tvSeriesId
        }
    }

    fun getMovieById(movieId: Long): Movie? {
        return movieList.find { movie ->
            movie.id == movieId
        }
    }
}