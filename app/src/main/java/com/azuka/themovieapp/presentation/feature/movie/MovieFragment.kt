package com.azuka.themovieapp.presentation.feature.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.feature.HomeFragmentDirections
import com.azuka.themovieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val viewModel: MovieViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_movie

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        viewModel.getMovieList().observe(this, { movieList ->
            val adapter = MovieAdapter(movieList) { movie ->
                parentFragment?.findNavController()?.navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        movie.id, Constants.Movie.TAG_MOVIE_TYPE
                    )
                )
            }
            loadingMovie.visibility = View.GONE
            rvMovie.adapter = adapter
        })
    }
}