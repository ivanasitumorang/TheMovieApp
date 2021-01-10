package com.azuka.themovieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.source.local.LocalDataSource
import com.azuka.themovieapp.data.source.remote.RemoteDataSource
import com.azuka.themovieapp.data.source.remote.response.MovieResponse
import com.azuka.themovieapp.data.source.remote.response.TvSeriesResponse
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import com.azuka.themovieapp.utils.CoroutineContextProvider
import com.azuka.themovieapp.utils.LiveDataTestUtil
import com.azuka.themovieapp.utils.PagedListUtil
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
    private val localSource: LocalDataSource = mockk()
    private val coroutineProvider: CoroutineContextProvider = mockk()

    private val repository = FakeRepository(
        remoteSource, localSource, coroutineProvider
    )


    @Test
    fun `get movie list with empty value`() {
        val baseResponseMovie = BaseResponse<MovieResponse>()

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
        assertEquals(0, movieListValue.size)
    }

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

    @Test
    fun `get tv series list with empty value`() {
        val baseResponseTvSeries = BaseResponse<TvSeriesResponse>()

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
        assertEquals(0, tvSeriesListValue.size)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `get movie detail by id = '761053' successfully`() {
        val movieId = 761053L
        val movieResponse = TestUtils.getMovieResponseDetailData(movieId)

        every { remoteSource.getMovieDetail(movieId, any()) }
            .answers {
                // args 0 => params 'movieId: Long', args 1 => 'onReceived: (MovieResponse) -> Unit'
                (invocation.args[1] as (MovieResponse) -> Unit)
                    .invoke(movieResponse)
            }

        val movieDetailValue = LiveDataTestUtil.getValue(repository.getMovieDetail(movieId))
        verify {
            remoteSource.getMovieDetail(movieId, any())
        }

        assertNotNull(movieDetailValue)
        assertEquals(movieResponse.id, movieDetailValue.id)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `get tv series detail by id = '100' successfully`() {
        val tvSeriesId = 100L
        val tvSeriesResponse = TestUtils.getTvSeriesResponseDetailData(tvSeriesId)

        every { remoteSource.getTvSeriesDetail(tvSeriesId, any()) }
            .answers {
                // args 0 => params 'tvSeriesId: Long', args 1 => 'onReceived: (TvSeriesResponse) -> Unit'
                (invocation.args[1] as (TvSeriesResponse) -> Unit)
                    .invoke(tvSeriesResponse)
            }

        val tvSeriesDetailValue =
            LiveDataTestUtil.getValue(repository.getTvSeriesDetail(tvSeriesId))
        verify {
            remoteSource.getTvSeriesDetail(tvSeriesId, any())
        }

        assertNotNull(tvSeriesDetailValue)
        assertEquals(tvSeriesResponse.id, tvSeriesDetailValue.id)
    }

    @Test
    fun `get favorites movie from db`() {
        val favoriteGeneralList = listOf(
            FavoriteGeneral(
                id = 1L,
                title = "dummy title",
                overview = "dummy overview",
                voteAverage = 5.8,
                voteCount = 100,
                releaseDate = "01-01-2020",
                originalLanguage = "en",
                posterPath = "dummy url"
            )
        )
        val dataSourceFactory: DataSource.Factory<Int, FavoriteGeneral> = mockk()

        every { localSource.getFavoriteMovies() }
            .returns(dataSourceFactory)

        repository.getFavoriteMovies()

        val favoriteList = PagedListUtil.mockPagedList(favoriteGeneralList)

        verify {
            localSource.getFavoriteMovies()
        }

        assertNotNull(favoriteList)
        assertEquals(favoriteGeneralList.size, favoriteList.size)
    }

    @Test
    fun `get favorites movie from db BUT no favorite movies`() {
        val favoriteGeneralList = emptyList<FavoriteGeneral>()
        val dataSourceFactory: DataSource.Factory<Int, FavoriteGeneral> = mockk()

        every { localSource.getFavoriteMovies() }
            .returns(dataSourceFactory)

        repository.getFavoriteMovies()

        val favoriteList = PagedListUtil.mockPagedList(favoriteGeneralList)

        verify {
            localSource.getFavoriteMovies()
        }

        assertNotNull(favoriteList)
        assertEquals(0, favoriteList.size)
    }

    @Test
    fun `get favorites tv series from db`() {
        val favoriteGeneralList = listOf(
            FavoriteGeneral(
                id = 1L,
                title = "dummy title",
                overview = "dummy overview",
                voteAverage = 5.8,
                voteCount = 100,
                releaseDate = "01-01-2020",
                originalLanguage = "en",
                posterPath = "dummy url"
            )
        )
        val dataSourceFactory: DataSource.Factory<Int, FavoriteGeneral> = mockk()

        every { localSource.getFavoriteTvSeries() }
            .returns(dataSourceFactory)

        repository.getFavoriteTvSeries()

        val favoriteList = PagedListUtil.mockPagedList(favoriteGeneralList)

        verify {
            localSource.getFavoriteTvSeries()
        }

        assertNotNull(favoriteList)
        assertEquals(favoriteGeneralList.size, favoriteList.size)
    }

    @Test
    fun `get favorites tv series from db BUT no favorite tv series`() {
        val favoriteGeneralList = emptyList<FavoriteGeneral>()
        val dataSourceFactory: DataSource.Factory<Int, FavoriteGeneral> = mockk()

        every { localSource.getFavoriteTvSeries() }
            .returns(dataSourceFactory)

        repository.getFavoriteTvSeries()

        val favoriteList = PagedListUtil.mockPagedList(favoriteGeneralList)

        verify {
            localSource.getFavoriteTvSeries()
        }

        assertNotNull(favoriteList)
        assertEquals(0, favoriteList.size)
    }

}