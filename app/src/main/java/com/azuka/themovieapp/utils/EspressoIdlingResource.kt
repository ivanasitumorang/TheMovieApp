package com.azuka.themovieapp.utils

import androidx.test.espresso.idling.CountingIdlingResource


/**
 * Created by ivanaazuka on 31/12/20.
 * Android Engineer
 */

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"

    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}