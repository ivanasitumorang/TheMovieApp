package com.azuka.themovieapp.di

import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.data.source.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


/**
 * Created by ivanaazuka on 18/12/20.
 * Android Engineer
 */

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideRepository(repository: RepositoryImpl): Repository
//
//    @Binds
//    abstract fun provideRemoteDataSource
}