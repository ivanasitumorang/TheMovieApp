package com.azuka.themovieapp.feature.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.BuildConfig
import com.azuka.themovieapp.R
import com.azuka.themovieapp.data.Movie
import com.azuka.themovieapp.data.TvSeries
import com.azuka.themovieapp.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class DetailFragment : BaseFragment() {

    private var dataType: String? = null
    private var movieId: Long? = null

    private val viewModel: DetailViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbarDetail.setupWithNavController(navController, appBarConfiguration)
    }

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
            val moviePosterPath = "${BuildConfig.TMDB_IMAGE_URL}/w500/${movie.posterPath}"
            Picasso.get().load(moviePosterPath).into(ivDetailImage)
            tvDetailTitle.text = movie.title
            rbDetail.rating = (movie.voteAverage / 2).toFloat()
            tvDetailRating.text = ("(${movie.voteAverage} of 10)")
            tvDetailReleaseDate.text = movie.releaseDate
            tvDetailLanguage.text = movie.originalLanguage
            tvDetailVoteCount.text = movie.voteCount.toString()
            tvDetailOverview.text = movie.overview

        }
    }

    private fun setupContentTvSeries(tvSeries: TvSeries?) {
        tvSeries?.let {
            val seriesPosterPath = "${BuildConfig.TMDB_IMAGE_URL}/w500/${tvSeries.posterPath}"
            Picasso.get().load(seriesPosterPath).into(ivDetailImage)
            tvDetailTitle.text = tvSeries.name
            rbDetail.rating = (tvSeries.voteAverage / 2).toFloat()
            tvDetailRating.text = ("(${tvSeries.voteAverage} of 10)")
            tvDetailReleaseDate.text = tvSeries.firstAirDate
            tvDetailLanguage.text = tvSeries.originalLanguage
            tvDetailVoteCount.text = tvSeries.voteCount.toString()
            tvDetailOverview.text = tvSeries.overview
        }
    }
}