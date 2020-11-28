package com.azuka.themovieapp.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.azuka.themovieapp.R
import com.azuka.themovieapp.utils.DummyData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ivanaazuka on 28/11/20.
 * Android Engineer
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private val movieList = DummyData.getDummyMovies().results
    private val tvSeriesList = DummyData.getDummyTvSeries().results

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_loadMovieList() {
        onView(withId(R.id.rvMovie))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieList.size))

    }


    @Test
    fun test_loadTvSeriesList() {
        onView(withText("Tv Series")).perform(click())

        onView(withId(R.id.rvTvSeries))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvTvSeries))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<RecyclerView.ViewHolder>(tvSeriesList.size)
            )
    }
}