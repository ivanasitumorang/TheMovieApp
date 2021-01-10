package com.azuka.themovieapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.favorites.FavoriteViewModel
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by ivanaazuka on 10/01/21.
 * Android Engineer
 */

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<FavoriteGeneral>>

    @Mock
    private lateinit var observerFavoriteStatus: Observer<Boolean>

    @Mock
    private lateinit var pagedList: PagedList<FavoriteGeneral>

    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(mockRepository)
    }

    @Test
    fun `add movie to favorite successfully`() {
        val dummyMovie = Movie(
            id = 1L,
            title = "dummy title",
            overview = "dummy overview",
            voteAverage = 5.8,
            voteCount = 100,
            releaseDate = "01-01-2020",
            originalLanguage = "en",
            posterPath = "dummy url"
        )

        doNothing()
            .`when`(mockRepository)
            .insertFavoriteMovie(dummyMovie)

        viewModel.addMovieToFavorite(dummyMovie)

        verify(mockRepository).insertFavoriteMovie(dummyMovie)
    }

    @Test
    fun `remove movie with id '123' from favorite successfully`() {
        val movieId = 123L

        doNothing()
            .`when`(mockRepository)
            .removeFavoriteMovie(movieId)

        viewModel.removeMovieFromFavorite(movieId)

        verify(mockRepository).removeFavoriteMovie(movieId)
    }

    @Test
    fun `add tv series to favorite successfully`() {
        val dummyTvSeries = TvSeries(
            id = 1L,
            name = "dummy name",
            overview = "dummy overview",
            voteAverage = 5.8,
            voteCount = 100,
            firstAirDate = "01-01-2020",
            originalLanguage = "en",
            posterPath = "dummy url"
        )

        doNothing()
            .`when`(mockRepository)
            .insertFavoriteTvSeries(dummyTvSeries)

        viewModel.addTvSeriesToFavorite(dummyTvSeries)

        verify(mockRepository).insertFavoriteTvSeries(dummyTvSeries)
    }

    @Test
    fun `remove tv series with id '654' from favorite successfully`() {
        val tvSeriesId = 654L

        doNothing()
            .`when`(mockRepository)
            .removeFavoriteTvSeries(tvSeriesId)

        viewModel.removeTvSeriesFromFavorite(tvSeriesId)

        verify(mockRepository).removeFavoriteTvSeries(tvSeriesId)
    }

    @Test
    fun `get favorite movies successfully`() {
        val dummyMoviesSize = 3
        `when`(pagedList.size).thenReturn(dummyMoviesSize)
        val movieList = MutableLiveData<PagedList<FavoriteGeneral>>()
        movieList.value = pagedList

        `when`(mockRepository.getFavoriteMovies()).thenReturn(movieList)

        val favoriteListValue = viewModel.getMovieList().value

        verify(mockRepository).getFavoriteMovies()
        assertNotNull(favoriteListValue)
        assertEquals(dummyMoviesSize, favoriteListValue?.size)

        viewModel.getMovieList().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }

    @Test
    fun `get favorite movies with empty data`() {
        val dummyMoviesSize = 0
        `when`(pagedList.size).thenReturn(dummyMoviesSize)
        val movieList = MutableLiveData<PagedList<FavoriteGeneral>>()
        movieList.value = pagedList

        `when`(mockRepository.getFavoriteMovies()).thenReturn(movieList)

        val favoriteListValue = viewModel.getMovieList().value

        verify(mockRepository).getFavoriteMovies()
        assertNotNull(favoriteListValue)
        assertEquals(dummyMoviesSize, favoriteListValue?.size)

        viewModel.getMovieList().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }

    @Test
    fun `get favorite tv series successfully`() {
        val dummyTvSeriesSize = 10
        `when`(pagedList.size).thenReturn(dummyTvSeriesSize)
        val tvSeriesList = MutableLiveData<PagedList<FavoriteGeneral>>()
        tvSeriesList.value = pagedList

        `when`(mockRepository.getFavoriteTvSeries()).thenReturn(tvSeriesList)

        val favoriteListValue = viewModel.getTvSeries().value

        verify(mockRepository).getFavoriteTvSeries()
        assertNotNull(favoriteListValue)
        assertEquals(dummyTvSeriesSize, favoriteListValue?.size)

        viewModel.getTvSeries().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }

    @Test
    fun `get favorite tv series with empty data`() {
        val dummyTvSeriesSize = 0
        `when`(pagedList.size).thenReturn(dummyTvSeriesSize)
        val tvSeriesList = MutableLiveData<PagedList<FavoriteGeneral>>()
        tvSeriesList.value = pagedList

        `when`(mockRepository.getFavoriteTvSeries()).thenReturn(tvSeriesList)

        val favoriteListValue = viewModel.getTvSeries().value

        verify(mockRepository).getFavoriteTvSeries()
        assertNotNull(favoriteListValue)
        assertEquals(dummyTvSeriesSize, favoriteListValue?.size)

        viewModel.getTvSeries().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }

    @Test
    fun `check if movie with id = '123' is favorite`() {
        val movieId = 123L
        val isMovieFavorite = true
        val movieFavoriteStatus = MutableLiveData<Boolean>()
        movieFavoriteStatus.value = isMovieFavorite

        `when`(mockRepository.checkIfFavoriteMovie(movieId))
            .thenReturn(movieFavoriteStatus)

        val movieFavoriteValue = viewModel.checkIfFavoriteMovie(movieId).value

        verify(mockRepository).checkIfFavoriteMovie(movieId)
        assertNotNull(movieFavoriteValue)
        assertEquals(isMovieFavorite, movieFavoriteValue)

        viewModel.checkIfFavoriteMovie(movieId).observeForever(observerFavoriteStatus)
        verify(observerFavoriteStatus).onChanged(isMovieFavorite)
    }

    @Test
    fun `check if movie with id = '321' is NOT favorite`() {
        val movieId = 321L
        val isMovieFavorite = false
        val movieFavoriteStatus = MutableLiveData<Boolean>()
        movieFavoriteStatus.value = isMovieFavorite

        `when`(mockRepository.checkIfFavoriteMovie(movieId))
            .thenReturn(movieFavoriteStatus)

        val movieFavoriteValue = viewModel.checkIfFavoriteMovie(movieId).value

        verify(mockRepository).checkIfFavoriteMovie(movieId)
        assertNotNull(movieFavoriteValue)
        assertEquals(isMovieFavorite, movieFavoriteValue)

        viewModel.checkIfFavoriteMovie(movieId).observeForever(observerFavoriteStatus)
        verify(observerFavoriteStatus).onChanged(isMovieFavorite)
    }

    @Test
    fun `check if tv series with id = '890' is favorite`() {
        val tvSeriesId = 890L
        val isTvSeriesFavorite = true
        val tvSeriesFavoriteStatus = MutableLiveData<Boolean>()
        tvSeriesFavoriteStatus.value = isTvSeriesFavorite

        `when`(mockRepository.checkIfFavoriteTvSeries(tvSeriesId))
            .thenReturn(tvSeriesFavoriteStatus)

        val tvSeriesFavoriteValue = viewModel.checkIfFavoriteTvSeries(tvSeriesId).value

        verify(mockRepository).checkIfFavoriteTvSeries(tvSeriesId)
        assertNotNull(tvSeriesFavoriteValue)
        assertEquals(isTvSeriesFavorite, tvSeriesFavoriteValue)

        viewModel.checkIfFavoriteTvSeries(tvSeriesId).observeForever(observerFavoriteStatus)
        verify(observerFavoriteStatus).onChanged(isTvSeriesFavorite)
    }

    @Test
    fun `check if tv series with id = '98' is NOT favorite`() {
        val tvSeriesId = 98L
        val isTvSeriesFavorite = false
        val tvSeriesFavoriteStatus = MutableLiveData<Boolean>()
        tvSeriesFavoriteStatus.value = isTvSeriesFavorite

        `when`(mockRepository.checkIfFavoriteTvSeries(tvSeriesId))
            .thenReturn(tvSeriesFavoriteStatus)

        val tvSeriesFavoriteValue = viewModel.checkIfFavoriteTvSeries(tvSeriesId).value

        verify(mockRepository).checkIfFavoriteTvSeries(tvSeriesId)
        assertNotNull(tvSeriesFavoriteValue)
        assertEquals(isTvSeriesFavorite, tvSeriesFavoriteValue)

        viewModel.checkIfFavoriteTvSeries(tvSeriesId).observeForever(observerFavoriteStatus)
        verify(observerFavoriteStatus).onChanged(isTvSeriesFavorite)
    }
}