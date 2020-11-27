package com.azuka.themovieapp.presentation.feature.detail

import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.Dummy


/**
 * Created by ivanaazuka on 26/11/20.
 * Android Engineer
 */

class DetailViewModel(private val dummy: Dummy) : ViewModel() {
    fun getTvSeriesById(tvSeriesId: Long): TvSeries? {
        return getTvSeriesDummy().find { tvSeries ->
            tvSeries.id == tvSeriesId
        }
    }

    fun getMovieById(movieId: Long): Movie? {
        return getMoviesDummy().find { movie ->
            movie.id == movieId
        }
    }

    private fun getMoviesDummy(): List<Movie> {
        return dummy.getDummyMovies().results
    }

    private fun getTvSeriesDummy(): List<TvSeries> {
        return dummy.getDummyTvSeries().results
    }
}