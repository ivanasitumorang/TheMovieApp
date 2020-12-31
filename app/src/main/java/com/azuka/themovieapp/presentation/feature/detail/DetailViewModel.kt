package com.azuka.themovieapp.presentation.feature.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by ivanaazuka on 26/11/20.
 * Android Engineer
 */

class DetailViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private val _selectedTvSeries = MutableLiveData<TvSeries>()
    val selectedTvSeries: LiveData<TvSeries> = _selectedTvSeries

    private val _selectedMovie = MutableLiveData<Movie?>()
    val selectedMovie: LiveData<Movie?> = _selectedMovie


    private val movieList = repository.getMovies()

    fun getTvSeriesById(tvSeriesId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val tvSeriesList = repository.getTvSeries()
            Transformations.switchMap(tvSeriesList) { list ->
                val live = MutableLiveData<TvSeries>()
                val tvSeries = list.first { tvSeries -> tvSeries.id == tvSeriesId }
                live.value = tvSeries
                live
            }
        }
    }

    fun getMovieById(movieId: Long) {
        Transformations.switchMap(movieList) { list ->
            val live = MutableLiveData<Movie>()
            val movie = list.first { movie -> movie.id == movieId }
            live.value = movie
            live
        }
    }
}