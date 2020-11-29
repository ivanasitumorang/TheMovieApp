package com.azuka.themovieapp.data

import com.google.gson.annotations.SerializedName


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */
 
data class BaseResponse<T>(
    val page: Long = 1,
    @SerializedName("total_pages") val totalPages: Long = 1,
    val results: List<T> = emptyList()
)