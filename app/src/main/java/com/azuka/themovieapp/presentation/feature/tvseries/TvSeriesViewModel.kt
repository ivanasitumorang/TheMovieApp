package com.azuka.themovieapp.presentation.feature.tvseries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.DummyData


/**
 * Created by ivanaazuka on 25/11/20.
 * Android Engineer
 */

class TvSeriesViewModel(private val app: Application) : AndroidViewModel(app) {

    fun getTvSeriesDummy(): List<TvSeries> {
        return DummyData.getDummyTvSeries(app.applicationContext).results
    }
}