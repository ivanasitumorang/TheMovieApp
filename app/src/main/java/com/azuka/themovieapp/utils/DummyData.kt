package com.azuka.themovieapp.utils

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.extension.convert


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

interface Dummy {
    fun getDummyMovies(): BaseResponse<Movie>
    fun getDummyTvSeries(): BaseResponse<TvSeries>
}

object DummyData : Dummy {

    override fun getDummyMovies(): BaseResponse<Movie> =
        Utils.getJsonFromAssets(MovieType).convert()

    override fun getDummyTvSeries(): BaseResponse<TvSeries> =
        Utils.getJsonFromAssets(TvSeriesType).convert()
}

sealed class DataType
object MovieType : DataType()
object TvSeriesType : DataType()