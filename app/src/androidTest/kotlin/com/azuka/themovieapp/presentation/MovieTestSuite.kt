package com.azuka.themovieapp.presentation

import com.azuka.themovieapp.presentation.feature.movie.MovieFragmentTest
import com.azuka.themovieapp.presentation.feature.tvseries.TvSeriesFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


/**
 * Created by ivanaazuka on 29/11/20.
 * Android Engineer
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieFragmentTest::class,
    TvSeriesFragmentTest::class
)
class MovieTestSuite