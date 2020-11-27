package com.azuka.themovieapp.utils

import android.util.Log
import com.azuka.themovieapp.extension.convertStringToMap
import java.io.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

object Utils {

    fun getJsonFromAssets(dataType: DataType): Map<String, Any>? {
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