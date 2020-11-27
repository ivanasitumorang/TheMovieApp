package com.azuka.themovieapp.presentation.feature.tvseries

import androidx.lifecycle.ViewModel
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.DummyData


/**
 * Created by ivanaazuka on 25/11/20.
 * Android Engineer
 */

class TvSeriesViewModel : ViewModel() {

    fun getTvSeriesDummy(): List<TvSeries> {
        return DummyData.getDummyTvSeries().results
    }
}