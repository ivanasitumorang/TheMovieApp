package com.azuka.themovieapp.presentation.feature.movie.utils

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.extension.convertStringToMap
import com.azuka.themovieapp.extension.toDataClass
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter


/**
 * Created by ivanaazuka on 27/11/20.
 * Android Engineer
 */

object TestUtils {
    fun getEmptyMovieData() = emptyList<Movie>()

    fun getMovieDetailData(movieId: Long): Movie {
        return getMovieDataFromJson().first { movie ->
            movie.id == movieId
        }
    }

    fun getTvSeriesDetailData(tvSeriesId: Long): TvSeries {
        return getTvSeriesDataFromJson().first { tvSeries ->
            tvSeries.id == tvSeriesId
        }
    }

    fun getMovieDataFromJson(): List<Movie> {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("movie_data_response.json")

        val writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val jsonString = writer.toString()

        val dataInMap: Map<String, Any> = jsonString.convertStringToMap()
        val baseResponseMovie = dataInMap.toDataClass<BaseResponse<Movie>>()

        return baseResponseMovie.results
    }

    fun getEmptyTvSeriesData() = emptyList<TvSeries>()

    fun getTvSeriesDataFromJson(): List<TvSeries> {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("tv_series_data_response.json")

        val writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val jsonString = writer.toString()

        val dataInMap: Map<String, Any> = jsonString.convertStringToMap()
        val baseResponseTvSeries = dataInMap.toDataClass<BaseResponse<TvSeries>>()

        return baseResponseTvSeries.results
    }
}