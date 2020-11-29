package com.azuka.themovieapp.presentation.feature.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.MainActivity
import com.azuka.themovieapp.utils.DummyData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ivanaazuka on 29/11/20.
 * Android Engineer
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieFragmentTest {

    private val movieList = DummyData.getDummyMovies().results
    private val selectedMovieIndex = 2

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_loadMovieList() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieList.size))
    }

    @Test
    fun test_loadMovieDetail() {
        onView(withId(R.id.rvMovie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    selectedMovieIndex,
                    click()
                )
            )
        onView(withId(R.id.tvDetailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailTitle))
            .check(matches(withText(movieList[selectedMovieIndex].title)))
    }
}