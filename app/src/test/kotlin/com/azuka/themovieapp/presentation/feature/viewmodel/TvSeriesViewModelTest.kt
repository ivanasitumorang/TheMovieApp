package com.azuka.themovieapp.presentation.feature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.movie.utils.TestUtils
import com.azuka.themovieapp.presentation.feature.tvseries.TvSeriesViewModel
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
class TvSeriesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvSeries>>

    private lateinit var viewModel: TvSeriesViewModel

    @Before
    fun setUp() {
        viewModel = TvSeriesViewModel(mockRepository)
    }

    @Test
    fun `get movie list successfuly`() {
        val dummyTvSeries = TestUtils.getTvSeriesDataFromJson()
        val tvSeriesList = MutableLiveData<List<TvSeries>>()
        tvSeriesList.value = dummyTvSeries

        `when`(mockRepository.getTvSeries()).thenReturn(tvSeriesList)
        val tvSeriesListValue = viewModel.getTvSeries().value
        verify(mockRepository).getTvSeries()
        assertNotNull(tvSeriesListValue)
        assertEquals(dummyTvSeries.size, tvSeriesListValue?.size)

        viewModel.getTvSeries().observeForever(observer)
        verify(observer).onChanged(dummyTvSeries)
    }

    @Test
    fun `get movie list with empty list`() {
        val dummyTvSeries = TestUtils.getEmptyTvSeriesData()
        val tvSeriesList = MutableLiveData<List<TvSeries>>()
        tvSeriesList.value = dummyTvSeries

        `when`(mockRepository.getTvSeries()).thenReturn(tvSeriesList)
        val tvSeriesListValue = viewModel.getTvSeries().value
        verify(mockRepository).getTvSeries()
        assertNotNull(tvSeriesListValue)
        assertEquals(0, tvSeriesListValue?.size)

        viewModel.getTvSeries().observeForever(observer)
        verify(observer).onChanged(dummyTvSeries)
    }
}