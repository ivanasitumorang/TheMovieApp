package com.azuka.themovieapp.presentation.feature.movie

import com.azuka.themovieapp.data.BaseResponse
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.movie.utils.TestUtils
import com.azuka.themovieapp.presentation.feature.movie.utils.mock
import com.azuka.themovieapp.presentation.feature.tvseries.TvSeriesViewModel
import com.azuka.themovieapp.utils.Dummy
import org.junit.Assert.assertEquals
import org.mockito.Mockito.doReturn
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature


/**
 * Created by ivanaazuka on 28/11/20.
 * Android Engineer
 */

class TvSeriesViewModelTest : Spek({
    Feature("Tv Series View Model") {
        val dummy: Dummy = mock()
        val viewModel by memoized { TvSeriesViewModel(dummy) }

        Scenario("Successfully get all tv series data") {
            val tvSeriesList = TestUtils.getTvSeriesDataFromJson()

            lateinit var result: List<TvSeries>

            Given("the list of tv series should be returned") {
                doReturn(BaseResponse(results = tvSeriesList))
                    .`when`(dummy)
                    .getDummyTvSeries()
            }

            When("trigger get all tv series data") {
                result = viewModel.getTvSeriesDummy()
            }

            Then("should return list of tv series result") {
                assertEquals(tvSeriesList, result)
            }
        }

        Scenario("Successfully get all tv series data but is empty") {

            val emptyTvSeriesList = TestUtils.getEmptyTvSeriesData()

            lateinit var result: List<TvSeries>

            Given("the empty data should be returned") {
                doReturn(BaseResponse<TvSeries>())
                    .`when`(dummy)
                    .getDummyTvSeries()
            }

            When("trigger get all tv series data") {
                result = viewModel.getTvSeriesDummy()
            }

            Then("should return empty list of tv series") {
                assertEquals(emptyTvSeriesList, result)
            }
        }

        Scenario("Failed get all tv series data") {

            val emptyTvSeriesList = TestUtils.getEmptyTvSeriesData()

            lateinit var result: List<TvSeries>

            Given("the empty data should be returned when failed") {
                doReturn(BaseResponse<TvSeries>())
                    .`when`(dummy)
                    .getDummyTvSeries()
            }

            When("trigger get all tv series data") {
                result = viewModel.getTvSeriesDummy()
            }

            Then("should return empty list of tv series") {
                assertEquals(emptyTvSeriesList, result)
            }
        }
    }
})