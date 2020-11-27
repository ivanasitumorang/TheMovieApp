package com.azuka.themovieapp.presentation.feature.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.utils.DummyData


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieViewModel(private val app: Application) : AndroidViewModel(app) {

    fun getMoviesDummy(): List<Movie> {
        return DummyData.getDummyMovies(app.applicationContext).results
    }
}