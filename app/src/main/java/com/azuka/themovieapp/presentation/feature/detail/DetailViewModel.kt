package com.azuka.themovieapp.presentation.feature.detail

import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.DummyData


/**
 * Created by ivanaazuka on 26/11/20.
 * Android Engineer
 */

class DetailViewModel : ViewModel() {
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
        return DummyData.getDummyMovies().results
    }

    private fun getTvSeriesDummy(): List<TvSeries> {
        return DummyData.getDummyTvSeries().results
    }
}