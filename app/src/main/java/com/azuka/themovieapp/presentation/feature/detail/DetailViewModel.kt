package com.azuka.themovieapp.presentation.feature.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.DummyData


/**
 * Created by ivanaazuka on 26/11/20.
 * Android Engineer
 */

class DetailViewModel(private val app: Application) : AndroidViewModel(app) {
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
        return DummyData.getDummyMovies(app.applicationContext).results
    }

    private fun getTvSeriesDummy(): List<TvSeries> {
        return DummyData.getDummyTvSeries(app.applicationContext).results
    }
}