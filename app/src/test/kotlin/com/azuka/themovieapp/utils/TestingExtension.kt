package com.azuka.themovieapp.utils

import org.mockito.Mockito


/**
 * Created by ivanaazuka on 27/11/20.
 * Android Engineer
 */

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)