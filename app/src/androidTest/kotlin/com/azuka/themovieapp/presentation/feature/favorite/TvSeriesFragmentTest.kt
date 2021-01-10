package com.azuka.themovieapp.presentation.feature.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.MainActivity
import com.azuka.themovieapp.utils.EspressoIdlingResource
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by ivanaazuka on 11/01/21.
 * Android Engineer
 */

class TvSeriesFragmentTest {
    @get:Rule
    val scenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test_loadFavoriteTvSeriesList() {
        // open tab Tv Series in Home Page
        onView(withText("Tv Series")).perform(click())

        // make sure data favorite is available
        for (index in 0..10) {
            addTvSeriesToFavorite(index)
        }

        // go to favorite page
        onView(withId(R.id.favoritesFragment)).perform(click())

        // open tab Tv Series in Favorite Page
        onView(withText("Tv Series")).perform(click())

        // check the favorite tv series list is displayed
        onView(withId(R.id.rvTvSeries))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click()
                )
            )

        // check the title of the detail tv series
        onView(withId(R.id.tvDetailTitle))
            .check(matches(isDisplayed()))
            .check(matches(Matchers.not(withText(""))))
    }

    private fun addTvSeriesToFavorite(index: Int) {
        // open detail tv series
        onView(withId(R.id.rvTvSeries))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )

        // click favorite button
        onView(withId(R.id.btnFavorite)).perform(click())

        // back to tv series list
        onView(withContentDescription(R.string.abc_action_bar_up_description))
            .perform(click())

    }
}