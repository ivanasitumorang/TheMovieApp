package com.azuka.themovieapp.data.source.remote.network

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ivanaazuka on 23/12/20.
 * Android Engineer
 */

interface AppNetworkService {

    @GET("/3/tv/popular")
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<TvSeriesResponse>>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<MovieResponse>>
}