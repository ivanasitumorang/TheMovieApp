package com.azuka.themovieapp.presentation.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by ivanaazuka on 10/01/21.
 * Android Engineer
 */

@Parcelize
data class FavoriteGeneral(
    val id: Long,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Long,
    val releaseDate: String,
    val originalLanguage: String,
    val posterPath: String,
) : Parcelable