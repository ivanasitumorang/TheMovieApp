package com.azuka.themovieapp.presentation.feature.tvseries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
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
            favoriteViewModel.getTvSeries().observe(this, { favoriteList ->
                populateFavoriteTvSeriesList(favoriteList)
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
                navigateToDetail(tvSeries)
            }
            hideLoading()
            rvTvSeries.adapter = adapter
        }
    }

    private fun populateFavoriteTvSeriesList(favoriteList: PagedList<FavoriteGeneral>) {
        val adapter = FavoriteListAdapter { favoriteData ->
            navigateToDetail(favoriteData)
        }

        adapter.submitList(favoriteList)
        hideLoading()
        rvTvSeries.adapter = adapter
    }

    private fun hideLoading() {
        loadingTvSeries.visibility = View.GONE
    }

    private fun navigateToDetail(data: Any) {
        val action = if (isFavoriteScreen) {
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                favoriteData = data as FavoriteGeneral,
                dataType = Constants.Movie.TAG_TV_SERIES_TYPE
            )
        } else {
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                (data as TvSeries).id, Constants.Movie.TAG_TV_SERIES_TYPE
            )
        }
        navController.navigate(action)
    }
}