package com.azuka.themovieapp.presentation.feature.tvseries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.feature.HomeFragmentDirections
import com.azuka.themovieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tv_series.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

@AndroidEntryPoint
class TvSeriesFragment : BaseFragment() {

    private val viewModel: TvSeriesViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_tv_series

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        setupUI()
        viewModel.getTvSeries()
    }

    private fun setupUI() {
        viewModel.getTvSeries().observe(this, { tvSeriesList ->
            tvSeriesList?.let {
                val adapter = TvSeriesAdapter(tvSeriesList) { tvSeries ->
                    parentFragment?.findNavController()?.navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                            tvSeries.id, Constants.Movie.TAG_TV_SERIES_TYPE
                        )
                    )
                }
                loadingTvSeries.visibility = View.GONE
                rvTvSeries.adapter = adapter
            }
        })
    }
}