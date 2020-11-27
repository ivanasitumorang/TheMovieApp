package com.azuka.themovieapp.presentation.feature.tvseries

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.feature.HomeFragmentDirections
import com.azuka.themovieapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_tv_series.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class TvSeriesFragment : BaseFragment() {

    private val viewModel: TvSeriesViewModel by viewModels()

    override val viewLayout: Int = R.layout.fragment_tv_series

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        val tvSeriesList = viewModel.getTvSeriesDummy()
        val adapter = TvSeriesAdapter(tvSeriesList) { tvSeries ->
            parentFragment?.findNavController()?.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    tvSeries.id, Constants.Movie.TAG_TV_SERIES_TYPE
                )
            )
        }
        rvTvSeries.adapter = adapter
    }
}