package com.azuka.themovieapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azuka.themovieapp.presentation.feature.detail.DetailViewModel
import com.azuka.themovieapp.presentation.feature.movie.MovieViewModel
import com.azuka.themovieapp.presentation.feature.tvseries.TvSeriesViewModel


/**
 * Created by ivanaazuka on 28/11/20.
 * Android Engineer
 */

class MovieViewModelFactory(private val dummy: Dummy) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(dummy) as T
            }

            modelClass.isAssignableFrom(TvSeriesViewModel::class.java) -> {
                return TvSeriesViewModel(dummy) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(dummy) as T
            }
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}