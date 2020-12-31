package com.azuka.themovieapp.data.source

import androidx.lifecycle.LiveData
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
}