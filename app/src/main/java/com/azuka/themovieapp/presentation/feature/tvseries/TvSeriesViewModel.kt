package com.azuka.themovieapp.presentation.feature.tvseries

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.TvSeries


/**
 * Created by ivanaazuka on 25/11/20.
 * Android Engineer
 */

class TvSeriesViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private val _tvSeriesList = MediatorLiveData<List<TvSeries>>()
    val tvSeriesList: LiveData<List<TvSeries>> = _tvSeriesList

    fun getTvSeries(): LiveData<List<TvSeries>> = repository.getTvSeries()


}