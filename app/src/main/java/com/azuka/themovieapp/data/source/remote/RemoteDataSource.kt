package com.azuka.themovieapp.data.source.remote

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.remote.network.AppNetworkService
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

class RemoteDataSource @Inject constructor(private val networkService: AppNetworkService) {

    fun getMovies(callback: LoadMovieCallback) {
        var result = BaseResponse<MovieResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            networkService.getPopularMovies(
                apiKey = "edf78280d6daf8a04ce207ab946a53df"
            ).apply {
                if (isSuccessful) {
                    val responseBody = body() as BaseResponse<MovieResponse>
                    result = responseBody
                }
            }

            callback.onMovieReceived(result)
        }
    }

    fun getTvSeries(callback: LoadTvSeriesCallback) {
        var result = BaseResponse<TvSeriesResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            networkService.getPopularTvSeries(
                apiKey = "edf78280d6daf8a04ce207ab946a53df"
            ).apply {
                if (isSuccessful) {
                    val responseBody = body() as BaseResponse<TvSeriesResponse>
                    result = responseBody
                }
            }

            callback.onTvSeriesReceived(result)
        }
    }

    interface LoadTvSeriesCallback {
        fun onTvSeriesReceived(tvSeriesResponse: BaseResponse<TvSeriesResponse>)
    }

    interface LoadMovieCallback {
        fun onMovieReceived(movieResponse: BaseResponse<MovieResponse>)
    }
}