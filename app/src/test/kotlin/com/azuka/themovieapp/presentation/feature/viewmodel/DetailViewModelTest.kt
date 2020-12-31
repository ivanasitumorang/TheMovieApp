package com.azuka.themovieapp.presentation.feature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.detail.DetailViewModel
import com.azuka.themovieapp.presentation.feature.movie.utils.TestUtils
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by ivanaazuka on 31/12/20.
 * Android Engineer
 */

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var observerMovieDetail: Observer<Movie>

    @Mock
    private lateinit var observerTvSeriesDetail: Observer<TvSeries>

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(mockRepository)
    }

    @Test
    fun `get movie detail by id = '761053' successfully`() {
        val movieId = 761053L
        viewModel.setSelectedMovie(movieId)

        val dummyMovieDetail = TestUtils.getMovieDetailData(movieId = movieId)
        val movieDetail = MutableLiveData<Movie>()
        movieDetail.value = dummyMovieDetail

        `when`(mockRepository.getMovieDetail(movieId)).thenReturn(movieDetail)

        val movieDetailValue = viewModel.getMovieDetail().value
        verify(mockRepository).getMovieDetail(movieId)

        assertNotNull(movieDetailValue)
        assertEquals(dummyMovieDetail.title, movieDetailValue?.title)

        viewModel.getMovieDetail().observeForever(observerMovieDetail)
        verify(observerMovieDetail).onChanged(dummyMovieDetail)
    }

    @Test
    fun `get tv series detail by id = '100' successfully`() {
        val tvSeriesId = 100L
        viewModel.setSelectedTvSeriesId(tvSeriesId)

        val dummyTvSeriesDetail = TestUtils.getTvSeriesDetailData(tvSeriesId = tvSeriesId)
        val tvSeriesDetail = MutableLiveData<TvSeries>()
        tvSeriesDetail.value = dummyTvSeriesDetail

        `when`(mockRepository.getTvSeriesDetail(tvSeriesId)).thenReturn(tvSeriesDetail)

        val tvSeriesDetailValue = viewModel.getTvSeriesDetail().value
        verify(mockRepository).getTvSeriesDetail(tvSeriesId)

        assertNotNull(tvSeriesDetailValue)
        assertEquals(dummyTvSeriesDetail.name, tvSeriesDetailValue?.name)

        viewModel.getTvSeriesDetail().observeForever(observerTvSeriesDetail)
        verify(observerTvSeriesDetail).onChanged(dummyTvSeriesDetail)
    }

}