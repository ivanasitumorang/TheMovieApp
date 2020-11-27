package com.azuka.themovieapp.presentation.feature.movie.utils

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.extension.convertStringToMap
import com.azuka.themovieapp.extension.toDataClass
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter


/**
 * Created by ivanaazuka on 27/11/20.
 * Android Engineer
 */

object TestUtils {
    fun getMovieData(size: Int = 1): List<Movie> = (0 until size).map {
        Movie(
            id = 1000,
            title = "azuka",
            overview = "azuka",
            voteAverage = 8.9,
            voteCount = 100,
            releaseDate = "27-11-2020",
            originalLanguage = "en",
            posterPath = "/url.jpg"
        )
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
}