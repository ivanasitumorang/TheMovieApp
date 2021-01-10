package com.azuka.themovieapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

@Entity(tableName = "t_tv_series")
class TvSeriesEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Long,
    val firstAirDate: String,
    val originalLanguage: String,
    val posterPath: String,
)