package com.azuka.themovieapp.data.source.remote.response


/**
 * Created by ivanaazuka on 22/09/20.
 * Android Engineer
 */

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorResponse: ErrorResponse) : ApiResponse<Nothing>()
    data class Empty(val code: Int, val message: String) : ApiResponse<Nothing>()
}