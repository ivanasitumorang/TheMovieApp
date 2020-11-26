package com.azuka.themovieapp.feature.detail

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.Constants


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */
 
class DetailFragment : BaseFragment() {

    private var dataType: String? = null
    private var movieId: Long? = null

    private val viewModel: DetailViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_detail

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        arguments?.let { args ->
            movieId = DetailFragmentArgs.fromBundle(args).movieId
            dataType = DetailFragmentArgs.fromBundle(args).dataType
        }

        setupUI()
    }

    private fun setupUI() {
        movieId?.let {
            if (dataType == Constants.Movie.TAG_MOVIE_TYPE) {
                val movie = viewModel.getMovieById(it)
                setupContentMovie(movie)
            } else {
                val tvSeries = viewModel.getTvSeriesById(it)
                setupContentTvSeries(tvSeries)
            }
        }
    }

    private fun setupContentMovie(movie: Movie?) {
        movie?.let {
            showToast(movie.title)
        }
    }

    private fun setupContentTvSeries(tvSeries: TvSeries?) {
        tvSeries?.let {
            showToast(tvSeries.name)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}