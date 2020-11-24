package com.azuka.themovieapp.feature.movie

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
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
            showToast(movie.title)
        }
        rvMovie.adapter = adapter
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}