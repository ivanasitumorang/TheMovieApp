package com.azuka.themovieapp.presentation.feature.favorite

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
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by ivanaazuka on 10/01/21.
 * Android Engineer
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieFragmentTest {

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
    fun test_loadFavoriteMovieList() {

        // make sure data favorite is available
        for (index in 0..10) {
            tabFavoriteButton(index)
        }

        tabFavoriteButton((3..5).random())

        // go to favorite page
        onView(withId(R.id.favoritesFragment)).perform(click())

        // check the favorite movie list is displayed & click first item
        onView(withId(R.id.rvMovie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        // check the title of the detail movie
        onView(withId(R.id.tvDetailTitle))
            .check(matches(isDisplayed()))
            .check(matches(Matchers.not(withText(""))))
    }

    private fun tabFavoriteButton(index: Int) {
        // open detail movie
        onView(withId(R.id.rvMovie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )

        // click favorite button
        onView(withId(R.id.btnFavorite)).perform(click())

        // back to movie list
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

    }
}