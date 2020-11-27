package com.azuka.themovieapp.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

val gson = Gson()

//convert an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

//convert a string json to a map
inline fun <reified M> String.convertStringToMap(): M {
    return gson.fromJson(this, object : TypeToken<M>() {}.type)
}