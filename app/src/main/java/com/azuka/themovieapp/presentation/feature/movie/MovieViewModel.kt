package com.azuka.themovieapp.presentation.feature.movie

import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.utils.Dummy


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieViewModel(private val dummy: Dummy) : ViewModel() {

    fun getMoviesDummy(): List<Movie> {
        return dummy.getDummyMovies().results
    }
}