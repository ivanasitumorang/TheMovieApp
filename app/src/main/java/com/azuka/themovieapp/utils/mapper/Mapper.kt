package com.azuka.themovieapp.utils.mapper


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

abstract class Mapper<Entity, Domain, Response> {

    abstract fun mapResponseToDomain(response: Response): Domain
    fun mapResponsesToDomains(responses: List<Response>): List<Domain> =
        responses.map { response ->
            mapResponseToDomain(response)
        }

    abstract fun mapDomainToEntity(dto: Domain): Entity
    fun mapDomainsToEntities(dtos: List<Domain>): List<Entity> =
        dtos.map { dto -> mapDomainToEntity(dto) }

    abstract fun mapResponseToEntity(response: Response): Entity
    fun mapResponsesToEntities(responses: List<Response>): List<Entity> =
        responses.map { response ->
            mapResponseToEntity(response)
        }

    abstract fun mapEntityToDomain(entity: Entity): Domain
    fun mapEntitiesToDomains(entities: List<Entity>): List<Domain> =
        entities.map { entity -> mapEntityToDomain(entity) }
}