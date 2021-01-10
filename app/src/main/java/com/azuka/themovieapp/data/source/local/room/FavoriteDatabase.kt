package com.azuka.themovieapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azuka.themovieapp.data.source.local.entity.MovieEntity
import com.azuka.themovieapp.data.source.local.entity.TvSeriesEntity


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

@Database(
    entities = [MovieEntity::class, TvSeriesEntity::class], version = 1, exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}