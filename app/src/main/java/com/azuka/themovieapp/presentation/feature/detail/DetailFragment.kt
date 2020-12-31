package com.azuka.themovieapp.presentation.feature.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

@AndroidEntryPoint
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
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getMovieDetail().observe(this, {
            Log.i("Coba", "$it")
            setupContentMovie(it)
        })

        viewModel.selectedTvSeries.observe(this, {
            setupContentTvSeries(it)
        })
    }

    private fun setupUI() {
        movieId?.let {
            if (dataType == Constants.Movie.TAG_MOVIE_TYPE) {
                viewModel.setSelectedMovie(it)
            } else {
                viewModel.getTvSeriesById(it)
            }
        }
    }

    private fun setupContentMovie(movie: Movie?) {
        movie?.let {
            Picasso.get().load(movie.posterPath).into(ivDetailImage)
            tvDetailTitle.text = movie.title
            rbDetail.rating = (movie.voteAverage / 2).toFloat()
            tvDetailRating.text = getString(R.string.item_votes, movie.voteAverage)
            tvDetailReleaseDate.text = movie.releaseDate
            tvDetailLanguage.text = movie.originalLanguage
            tvDetailVoteCount.text = movie.voteCount.toString()
            tvDetailOverview.text = movie.overview

        }
    }

    private fun setupContentTvSeries(tvSeries: TvSeries?) {
        tvSeries?.let {
            Picasso.get().load(tvSeries.posterPath).into(ivDetailImage)
            tvDetailTitle.text = tvSeries.name
            rbDetail.rating = (tvSeries.voteAverage / 2).toFloat()
            tvDetailRating.text = getString(R.string.item_votes, tvSeries.voteAverage)
            tvDetailReleaseDate.text = tvSeries.firstAirDate
            tvDetailLanguage.text = tvSeries.originalLanguage
            tvDetailVoteCount.text = tvSeries.voteCount.toString()
            tvDetailOverview.text = tvSeries.overview
        }
    }
}