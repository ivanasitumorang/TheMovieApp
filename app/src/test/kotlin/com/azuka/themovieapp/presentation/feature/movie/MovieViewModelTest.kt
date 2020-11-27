package com.azuka.themovieapp.presentation.feature.movie

import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.presentation.feature.movie.utils.TestUtils
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by ivanaazuka on 27/11/20.
 * Android Engineer
 */

object MovieViewModelTest : Spek({
    Feature("Movie View Model") {
        val viewModel by memoized { MovieViewModel() }

        Scenario("get all movie data") {
            val movieData = TestUtils.getMovieDataFromJson()

            lateinit var result: List<Movie>

            When("get all movie data") {
                result = viewModel.getMoviesDummy()
            }

            Then("should return list of movies result") {
                assertEquals(movieData, result)
            }
        }
    }
})