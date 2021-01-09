package com.azuka.themovieapp.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class CoroutineContextProvider @Inject constructor() {
    fun mainThreadDispatcher(): CoroutineContext = Dispatchers.Main
    fun backgroundDispatcher(): CoroutineContext = Dispatchers.IO
}
