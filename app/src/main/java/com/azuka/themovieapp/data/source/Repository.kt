package com.azuka.themovieapp.data.source

import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

interface Repository {
    fun getMovies(): List<Movie>
    fun getTvSeries(): List<TvSeries>
}