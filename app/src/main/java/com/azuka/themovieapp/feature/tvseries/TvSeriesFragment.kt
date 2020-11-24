package com.azuka.themovieapp.feature.tvseries

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
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
            showToast(tvSeries.name)
        }
        rvTvSeries.adapter = adapter
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}