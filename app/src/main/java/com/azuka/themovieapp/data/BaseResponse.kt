package com.azuka.themovieapp.data


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */
 
data class BaseResponse<T>(
    val page: Long,
    val totalPages: Long,
    val results: List<T>
)