package com.azuka.themovieapp.presentation.feature.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.Movie


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    fun getMovieList(): LiveData<List<Movie>> = repository.getMovies()
}