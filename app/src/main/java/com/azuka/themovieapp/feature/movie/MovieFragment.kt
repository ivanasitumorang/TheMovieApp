package com.azuka.themovieapp.feature.movie

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.feature.HomeFragmentDirections
import com.azuka.themovieapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieFragment : BaseFragment() {

    private val viewModel: MovieViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_movie

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        val movieList = viewModel.getMoviesDummy()
        val adapter = MovieAdapter(movieList) { movie ->
            parentFragment?.findNavController()?.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    movie.id, Constants.Movie.TAG_MOVIE_TYPE
                )
            )
        }
        rvMovie.adapter = adapter
    }
}