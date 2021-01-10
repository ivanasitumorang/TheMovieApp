package com.azuka.themovieapp.presentation.feature.tvseries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.TvSeries
import com.azuka.themovieapp.presentation.feature.HomeFragmentDirections
import com.azuka.themovieapp.presentation.feature.favorites.FavoriteViewModel
import com.azuka.themovieapp.presentation.feature.favorites.FavoritesFragmentDirections
import com.azuka.themovieapp.presentation.feature.movie.FavoriteListAdapter
import com.azuka.themovieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tv_series.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

@AndroidEntryPoint
class TvSeriesFragment : BaseFragment() {

    private val tvSeriesViewModel: TvSeriesViewModel by viewModels()

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var isFavoriteScreen = false

    override val viewLayout: Int = R.layout.fragment_tv_series

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        isFavoriteScreen = navController.currentDestination?.id == R.id.favoritesFragment
        setupUI()
    }

    private fun setupUI() {
        if (isFavoriteScreen) {
            favoriteViewModel.getTvSeries().observe(this, { tvSeriesList ->
                val adapter = FavoriteListAdapter { tvSeries ->
                    navigateToDetail(tvSeries.id)
                }

                adapter.submitList(tvSeriesList)

                loadingTvSeries.visibility = View.GONE
                rvTvSeries.adapter = adapter
            })
        } else {
            tvSeriesViewModel.getTvSeries().observe(this, { tvSeriesList ->
                populateTvSeriesList(tvSeriesList)
            })
        }

    }

    private fun populateTvSeriesList(tvSeriesList: List<TvSeries>?) {
        tvSeriesList?.let {
            val adapter = TvSeriesAdapter(tvSeriesList) { tvSeries ->
                navigateToDetail(tvSeries.id)
            }
            loadingTvSeries.visibility = View.GONE
            rvTvSeries.adapter = adapter
        }
    }

    private fun navigateToDetail(tvSeriesId: Long) {
        val action = if (isFavoriteScreen) {
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                tvSeriesId, Constants.Movie.TAG_TV_SERIES_TYPE
            )
        } else {
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                tvSeriesId, Constants.Movie.TAG_TV_SERIES_TYPE
            )
        }
        navController.navigate(action)
    }
}