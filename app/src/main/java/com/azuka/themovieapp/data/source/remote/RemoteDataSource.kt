package com.azuka.themovieapp.data.source.remote

import com.azuka.themovieapp.utils.JsonHelper
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

@Singleton
class RemoteDataSource @Inject constructor(private val jsonHelper: JsonHelper) {

    fun getMovies(): List<MovieResponse> = jsonHelper.getDummyMovies().results

    fun getTvSeries(): List<TvSeriesResponse> = jsonHelper.getDummyTvSeries().results
}