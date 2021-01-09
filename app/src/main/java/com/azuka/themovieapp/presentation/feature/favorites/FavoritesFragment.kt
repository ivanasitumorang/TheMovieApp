package com.azuka.themovieapp.presentation.feature.favorites

import android.os.Bundle
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.TabAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorites.*


/**
 * Created by ivanaazuka on 08/01/21.
 * Android Engineer
 */

@AndroidEntryPoint
class FavoritesFragment : BaseFragment() {
    override val viewLayout: Int = R.layout.fragment_favorites

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        setupTab()
    }

    private fun setupTab() {
        val adapter = TabAdapter(childFragmentManager)
        val pager = pagerFavorite
        pager.adapter = adapter
        tabLayoutFavorite.setupWithViewPager(pager)
    }
}