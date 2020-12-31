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

    private var movieId: Long = 0

    private val _selectedTvSeries = MutableLiveData<TvSeries>()
    val selectedTvSeries: LiveData<TvSeries> = _selectedTvSeries

    private val _selectedMovie = MutableLiveData<Movie?>()
    val selectedMovie: LiveData<Movie?> = _selectedMovie

    fun setSelectedMovie(movieId: Long) {
        this.movieId = movieId
    }

    fun getTvSeriesById(tvSeriesId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val tvSeriesDetail = repository.getTvSeriesDetail(tvSeriesId)
            Transformations.switchMap(tvSeriesDetail) {
                _selectedTvSeries.postValue(it)
                selectedTvSeries
            }
        }
    }

    fun getMovieDetail(): LiveData<Movie> = repository.getMovieDetail(movieId)
}