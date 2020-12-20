package com.azuka.themovieapp.utils

import android.util.Log
import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.remote.MovieResponse
import com.azuka.themovieapp.data.source.remote.TvSeriesResponse
import com.azuka.themovieapp.extension.convert
import com.azuka.themovieapp.extension.convertStringToMap
import java.io.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

@Singleton
class JsonHelper @Inject constructor() {

    fun getDummyMovies(): BaseResponse<MovieResponse> =
        getJsonFromAssets(MovieType).convert()

    fun getDummyTvSeries(): BaseResponse<TvSeriesResponse> =
        getJsonFromAssets(TvSeriesType).convert()

    private fun getJsonFromAssets(dataType: DataType): Map<String, Any>? {
        return try {
            when (dataType) {
                MovieType -> {
                    val inputStream =
                        javaClass.classLoader?.getResourceAsStream("movie_data.json")
                    convertJsonFileToString(
                        inputStream
                    ).convertStringToMap()
                }
                TvSeriesType -> {
                    val inputStream =
                        javaClass.classLoader?.getResourceAsStream("tv_series_data.json")
                    convertJsonFileToString(
                        inputStream
                    ).convertStringToMap()
                }
            }
        } catch (e: IOException) {
            Log.i("Assets", "error : ${e.message}")
            null
        }
    }

    private fun convertJsonFileToString(inputStream: InputStream?): String {
        val writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }
}