package com.azuka.themovieapp.utils

import android.content.Context
import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.extension.convert


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

object DummyData {

    fun getDummyMovies(context: Context): BaseResponse<Movie> =
        Utils.getJsonFromAssets(context, MovieType).convert()

    fun getDummyTvSeries(context: Context): BaseResponse<TvSeries> =
        Utils.getJsonFromAssets(context, TvSeriesType).convert()
}

sealed class DataType
object MovieType : DataType()
object TvSeriesType : DataType()