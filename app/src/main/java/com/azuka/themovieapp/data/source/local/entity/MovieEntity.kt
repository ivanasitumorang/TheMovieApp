package com.azuka.themovieapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

@Entity(tableName = "t_movie")
class MovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Long,
    val releaseDate: String,
    val originalLanguage: String,
    val posterPath: String,
)