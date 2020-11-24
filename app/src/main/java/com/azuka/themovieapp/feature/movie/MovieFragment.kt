package com.azuka.themovieapp.feature.movie

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieFragment : BaseFragment() {

    private val viewModel: MovieViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_movie

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        val result = viewModel.getMoviesDummy()
    }
}