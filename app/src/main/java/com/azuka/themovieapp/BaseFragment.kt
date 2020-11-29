package com.azuka.themovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */
 
abstract class BaseFragment: Fragment() {

    abstract val viewLayout: Int

    abstract fun onFragmentReady(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(viewLayout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onFragmentReady(savedInstanceState)
    }

}