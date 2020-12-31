package com.azuka.themovieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.azuka.themovieapp.data.source.remote.RemoteDataSource
import com.azuka.themovieapp.utils.LiveDataTestUtil
import com.azuka.themovieapp.utils.TestUtils
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test


/**
 * Created by ivanaazuka on 31/12/20.
 * Android Engineer
 */

class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteSource: RemoteDataSource = mockk()

    private val repository = FakeRepository(remoteSource)


    @Test
    fun `get movie list successfully`() {
        val baseResponseMovie = TestUtils.getBaseResponseMovieDataFromJson()

        every { remoteSource.getMovies(any()) }
            .answers {
                (invocation.args[0] as RemoteDataSource.LoadMovieCallback)
                    .onMovieReceived(baseResponseMovie)
            }

        val movieListValue = LiveDataTestUtil.getValue(repository.getMovies())
        verify {
            remoteSource.getMovies(any())
        }

        assertNotNull(movieListValue)
        assertEquals(baseResponseMovie.results.size, movieListValue.size)
    }

    @Test
    fun `get tv series list successfully`() {
        val baseResponseTvSeries = TestUtils.getBaseResponseTvSeriesDataFromJson()

        every { remoteSource.getTvSeries(any()) }
            .answers {
                (invocation.args[0] as RemoteDataSource.LoadTvSeriesCallback)
                    .onTvSeriesReceived(baseResponseTvSeries)
            }

        val tvSeriesListValue = LiveDataTestUtil.getValue(repository.getTvSeries())
        verify {
            remoteSource.getTvSeries(any())
        }

        assertNotNull(tvSeriesListValue)
        assertEquals(baseResponseTvSeries.results.size, tvSeriesListValue.size)
    }

}