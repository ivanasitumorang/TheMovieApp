package com.azuka.themovieapp.presentation.feature.movie

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.presentation.feature.movie.utils.TestUtils
import com.azuka.themovieapp.presentation.feature.movie.utils.mock
import com.azuka.themovieapp.utils.Dummy
import org.junit.Assert.assertEquals
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doReturn
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by ivanaazuka on 27/11/20.
 * Android Engineer
 */

object MovieViewModelTest : Spek({
    Feature("Movie View Model") {
        val dummy: Dummy = mock()
        val viewModel by memoized { MovieViewModel(dummy) }

        Scenario("Successfully get all movie data") {
            val movieList = TestUtils.getMovieDataFromJson()

            lateinit var result: List<Movie>

            Given("the list of movies should be returned") {
                doReturn(BaseResponse<Movie>())
                    .`when`(dummy)
                    .getDummyMovies()
            }

            When("trigger get all movie data") {
                `when`(dummy.getDummyMovies()).thenReturn(BaseResponse(results = movieList))
                result = viewModel.getMoviesDummy()
            }

            Then("should return list of movies result") {
                assertEquals(movieList, result)
            }
        }

        Scenario("Successfully get all movie data but is empty") {

            val emptyMovieList = TestUtils.getEmptyMovieData()

            lateinit var result: List<Movie>

            Given("the empty data should be returned") {
                doReturn(BaseResponse<Movie>())
                    .`when`(dummy)
                    .getDummyMovies()
            }

            When("trigger get all movie data") {
                result = viewModel.getMoviesDummy()
            }

            Then("should return empty list of movie") {
                assertEquals(emptyMovieList, result)
            }
        }

        Scenario("Failed get all movie data") {

            val emptyMovieList = TestUtils.getEmptyMovieData()

            lateinit var result: List<Movie>

            Given("the empty data should be returned when failed") {
                doReturn(BaseResponse<Movie>())
                    .`when`(dummy)
                    .getDummyMovies()
            }

            When("trigger get all movie data") {
                result = viewModel.getMoviesDummy()
            }

            Then("should return empty list of movie") {
                assertEquals(emptyMovieList, result)
            }
        }
    }
})