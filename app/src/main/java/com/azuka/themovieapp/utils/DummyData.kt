package com.azuka.themovieapp.utils

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.extension.convert


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

object DummyData {

    fun getDummyMovies(): BaseResponse<Movie> =
        Utils.getJsonFromAssets(MovieType).convert()

    fun getDummyTvSeries(): BaseResponse<TvSeries> =
        Utils.getJsonFromAssets(TvSeriesType).convert()
}

sealed class DataType
object MovieType : DataType()
object TvSeriesType : DataType()