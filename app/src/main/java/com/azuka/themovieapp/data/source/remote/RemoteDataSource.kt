package com.azuka.themovieapp.data.source.remote

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.remote.network.AppNetworkService
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import com.azuka.themovieapp.utils.EspressoIdlingResource
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
        EspressoIdlingResource.increment()
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
            EspressoIdlingResource.decrement()
        }
    }

    fun getTvSeries(callback: LoadTvSeriesCallback) {
        EspressoIdlingResource.increment()
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
            EspressoIdlingResource.decrement()
        }
    }

    fun getMovieDetail(movieId: Long, onReceived: (MovieResponse) -> Unit) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            networkService.getDetailMovies(
                apiKey = "edf78280d6daf8a04ce207ab946a53df",
                movieId = movieId
            ).apply {
                if (isSuccessful) {
                    val responseBody = body() as MovieResponse
                    onReceived.invoke(responseBody)
                }
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun getTvSeriesDetail(tvSeriesId: Long, onReceived: (TvSeriesResponse) -> Unit) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            networkService.getDetailTvSeries(
                apiKey = "edf78280d6daf8a04ce207ab946a53df",
                tvSeriesId = tvSeriesId
            ).apply {
                if (isSuccessful) {
                    val responseBody = body() as TvSeriesResponse
                    onReceived.invoke(responseBody)
                }
            }
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadTvSeriesCallback {
        fun onTvSeriesReceived(tvSeriesResponse: BaseResponse<TvSeriesResponse>)
    }

    interface LoadMovieCallback {
        fun onMovieReceived(movieResponse: BaseResponse<MovieResponse>)
    }
}