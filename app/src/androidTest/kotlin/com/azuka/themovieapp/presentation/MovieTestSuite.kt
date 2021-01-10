package com.azuka.themovieapp.presentation

import com.azuka.themovieapp.presentation.feature.home.MovieFragmentTest
import com.azuka.themovieapp.presentation.feature.home.TvSeriesFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import com.azuka.themovieapp.presentation.feature.favorite.MovieFragmentTest as FavoriteMovieFragmentTest
import com.azuka.themovieapp.presentation.feature.favorite.TvSeriesFragmentTest as FavoriteTvSeriesFragmentTest


/**
 * Created by ivanaazuka on 29/11/20.
 * Android Engineer
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieFragmentTest::class,
    TvSeriesFragmentTest::class,
    FavoriteMovieFragmentTest::class,
    FavoriteTvSeriesFragmentTest::class
)
class MovieTestSuite