package com.azuka.themovieapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azuka.themovieapp.data.source.local.entity.MovieEntity
import com.azuka.themovieapp.data.source.local.entity.TvSeriesEntity


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM t_movie")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM t_tv_series")
    fun getTvSeries(): LiveData<List<TvSeriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvSeries(tvSeriesEntity: TvSeriesEntity)

    @Query("DELETE FROM t_movie WHERE id = :id")
    fun deleteMovie(id: String)

    @Query("DELETE FROM t_tv_series WHERE id = :id")
    fun deleteTvSeries(id: String)
}