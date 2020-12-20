package com.azuka.themovieapp.presentation.feature.detail

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.movie.utils.TestUtils
import com.azuka.themovieapp.presentation.feature.movie.utils.mock
import com.azuka.themovieapp.utils.Dummy
import org.junit.Assert.assertEquals
import org.mockito.Mockito.doReturn
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by ivanaazuka on 28/11/20.
 * Android Engineer
 */


object DetailViewModelTest : Spek({

    val dummy: Dummy = mock()
    val viewModel by memoized { DetailViewModel(dummy) }

    Feature("Detail Movie View Model Test") {

        Scenario("Movie with movieId is exist") {
            val movieList = TestUtils.getRandomMovieData()
            val movieId = 0L
            val movie: Movie? = Movie(
                id = movieId,
                title = "azuka",
                overview = "azuka",
                voteAverage = 8.9,
                voteCount = 100,
                releaseDate = "27-11-2020",
                originalLanguage = "en",
                posterPath = "/url.jpg"
            )

            var result: Movie? = null

            Given("The list of movies data") {
                doReturn(BaseResponse(results = movieList))
                    .`when`(dummy)
                    .getDummyMovies()
            }

            When("Trigger get movie by id") {
                result = viewModel.getMovieById(movieId)
            }

            Then("Should return the movie") {
                assertEquals(movie, result)
            }
        }

        Scenario("Movie with movieId is not exist") {
            val movieList = TestUtils.getRandomMovieData()
            val movieId = 5L
            val movie: Movie? = null

            var result: Movie? = null

            Given("The list of movies data") {
                doReturn(BaseResponse(results = movieList))
                    .`when`(dummy)
                    .getDummyMovies()
            }

            When("Trigger get movie by id") {
                result = viewModel.getMovieById(movieId)
            }

            Then("Should return null") {
                assertEquals(movie, result)
            }
        }
    }

    Feature("Detail Tv Series View Model Test") {

        Scenario("Tv series with tvSeriesId is exist") {
            val tvSeriesList = TestUtils.getRandomTvSeriesData(3)
            val tvSeriesId = 2L

            val tvSeries: TvSeries? = TvSeries(
                id = tvSeriesId,
                name = "azuka",
                overview = "azuka",
                voteAverage = 8.9,
                voteCount = 100,
                firstAirDate = "27-11-2020",
                originalLanguage = "en",
                posterPath = "/url.jpg"
            )

            var result: TvSeries? = null

            Given("The list of tv series data") {
                doReturn(BaseResponse(results = tvSeriesList))
                    .`when`(dummy)
                    .getDummyTvSeries()
            }

            When("Trigger get tv series by id") {
                result = viewModel.getTvSeriesById(tvSeriesId)
            }

            Then("Should return the tv series") {
                assertEquals(tvSeries, result)
            }
        }

        Scenario("Tv series with movieId is not exist") {
            val tvSeriesList = TestUtils.getRandomTvSeriesData(3)
            val tvSeriesId = 20L

            val tvSeries: TvSeries? = null

            var result: TvSeries? = null

            Given("The list of tv series data") {
                doReturn(BaseResponse(results = tvSeriesList))
                    .`when`(dummy)
                    .getDummyTvSeries()
            }

            When("Trigger get tv series by id") {
                result = viewModel.getTvSeriesById(tvSeriesId)
            }

            Then("Should return null") {
                assertEquals(tvSeries, result)
            }
        }
    }
})