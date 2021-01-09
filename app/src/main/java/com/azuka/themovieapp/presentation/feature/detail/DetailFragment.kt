package com.azuka.themovieapp.presentation.feature.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.favorites.FavoriteViewModel
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

    private val detailViewModel: DetailViewModel by viewModels()

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var dataType: String? = null
    private var movieId: Long? = null

    private var movie: Movie? = null

    private var tvSeries: TvSeries? = null

    private var isFavorite = false

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
        setupUIListener()
    }

    private fun setupUIListener() {
        btnFavorite.setOnClickListener {
            when (dataType) {
                Constants.Movie.TAG_MOVIE_TYPE -> {
                    movie?.let {
                        if (isFavorite) favoriteViewModel.removeMovieFromFavorite(it.id)
                        else favoriteViewModel.addMovieToFavorite(it)
                    }
                }

                Constants.Movie.TAG_TV_SERIES_TYPE -> {
                    tvSeries?.let {
                        if (isFavorite) favoriteViewModel.removeTvSeriesFromFavorite(it.id)
                        else favoriteViewModel.addTvSeriesToFavorite(it)
                    }
                }
            }
        }
    }

    private fun setupObserver() {
        when (dataType) {
            Constants.Movie.TAG_MOVIE_TYPE -> {
                detailViewModel.getMovieDetail().observe(this, {
                    movie = it
                    setupContentMovie(it)
                    hideLoading()
                })

                movieId?.let { id ->
                    favoriteViewModel.checkIfFavoriteMovie(id).observe(this, { isFavorite ->
                        setFavoriteButtonState(isFavorite)
                    })
                }
            }

            Constants.Movie.TAG_TV_SERIES_TYPE -> {
                detailViewModel.getTvSeriesDetail().observe(this, {
                    tvSeries = it
                    setupContentTvSeries(it)
                    hideLoading()
                })

                movieId?.let { id ->
                    favoriteViewModel.checkIfFavoriteTvSeries(id).observe(this, { isFavorite ->
                        setFavoriteButtonState(isFavorite)
                    })
                }
            }
        }
    }

    private fun setFavoriteButtonState(isFavorite: Boolean) {
        this.isFavorite = isFavorite
        if (isFavorite) {
            btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.ic_heart_fill
                )
            )
        } else {
            btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.ic_heart_empty
                )
            )
        }
    }

    private fun setupUI() {
        movieId?.let {
            if (dataType == Constants.Movie.TAG_MOVIE_TYPE) {
                detailViewModel.setSelectedMovie(it)
            } else {
                detailViewModel.setSelectedTvSeriesId(it)
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

    private fun hideLoading() {
        loadingDetail.visibility = View.GONE
    }
}