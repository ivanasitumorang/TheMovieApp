package com.azuka.themovieapp.utils.mapper


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

abstract class Mapper<Domain, Response> {

    abstract fun mapResponseToDomain(response: Response): Domain
    fun mapResponsesToDomains(responses: List<Response>): List<Domain> =
        responses.map { response ->
            mapResponseToDomain(response)
        }
}