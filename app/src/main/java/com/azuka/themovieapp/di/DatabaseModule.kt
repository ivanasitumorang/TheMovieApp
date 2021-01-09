package com.azuka.themovieapp.di

import android.content.Context
import androidx.room.Room
import com.azuka.themovieapp.data.source.local.room.FavoriteDao
import com.azuka.themovieapp.data.source.local.room.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


/**
 * Created by ivanaazuka on 09/01/21.
 * Android Engineer
 */

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FavoriteDatabase = Room
        .databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "FavoriteDatabase.db"
        ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideFavoriteDao(database: FavoriteDatabase): FavoriteDao = database.favoriteDao()
}