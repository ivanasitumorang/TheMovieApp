package com.azuka.themovieapp.feature

import android.os.Bundle
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.TabAdapter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class HomeFragment : BaseFragment() {
    override val viewLayout: Int = R.layout.fragment_home

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        setupTab()
    }

    private fun setupTab() {
        val adapter = TabAdapter(childFragmentManager)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }

}