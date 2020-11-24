package com.azuka.themovieapp.utils

import android.content.Context
import android.util.Log
import com.azuka.themovieapp.R
import com.azuka.themovieapp.extension.convertStringToMap
import java.io.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class Utils {
    companion object {
        fun getJsonFromAssets(context: Context, dataType: DataType): Map<String, Any>? {
            return try {
                val resources = context.resources
                when (dataType) {
                    MovieType -> {
                        convertJsonFileToString(
                            resources.openRawResource(R.raw.movie_data)
                        ).convertStringToMap()
                    }
                    TvSeriesType -> {
                        convertJsonFileToString(
                            resources.openRawResource(R.raw.tv_series_data)
                        ).convertStringToMap()
                    }
                }
            } catch (e: IOException) {
                Log.i("Assets", "error : ${e.message}")
                null
            }
        }

        private fun convertJsonFileToString(inputStream: InputStream): String {
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
}