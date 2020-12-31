package com.azuka.themovieapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.azuka.themovieapp.data.source.Repository
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.feature.movie.MovieViewModel
import com.azuka.themovieapp.utils.TestUtils
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
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel(mockRepository)
    }

    @Test
    fun `get movie list successfuly`() {
        val dummyMovies = TestUtils.getMovieDataFromJson()
        val movieList = MutableLiveData<List<Movie>>()
        movieList.value = dummyMovies

        `when`(mockRepository.getMovies()).thenReturn(movieList)
        val movieListValue = viewModel.getMovieList().value
        verify(mockRepository).getMovies()
        assertNotNull(movieListValue)
        assertEquals(dummyMovies.size, movieListValue?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun `get movie list with empty list`() {
        val dummyMovies = TestUtils.getEmptyMovieData()
        val movieList = MutableLiveData<List<Movie>>()
        movieList.value = dummyMovies

        `when`(mockRepository.getMovies()).thenReturn(movieList)
        val movieListValue = viewModel.getMovieList().value
        verify(mockRepository).getMovies()
        assertNotNull(movieListValue)
        assertEquals(0, movieListValue?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}