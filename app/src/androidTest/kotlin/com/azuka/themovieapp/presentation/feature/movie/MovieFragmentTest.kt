package com.azuka.themovieapp.presentation.feature.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.MainActivity
import com.azuka.themovieapp.utils.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ivanaazuka on 29/11/20.
 * Android Engineer
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieFragmentTest {

    private val selectedMovieIndex = 2

    @get:Rule
    val scenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test_loadMovieList() {
        val itemCountToCheck = 10
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCountToCheck)
            )
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
        onView(withId(R.id.tvDetailTitle))
            .check(matches(isDisplayed()))
            .check(matches(not(withText(""))))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}