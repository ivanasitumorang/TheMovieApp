package com.azuka.themovieapp.data.source.remote.network

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("/3/movie/{movie_id}")
    suspend fun getDetailMovies(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getDetailTvSeries(
        @Path("tv_id") tvSeriesId: Long,
        @Query("api_key") apiKey: String
    ): Response<TvSeriesResponse>
}