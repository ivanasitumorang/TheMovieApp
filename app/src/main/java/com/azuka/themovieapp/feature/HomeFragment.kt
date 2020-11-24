package com.azuka.themovieapp.feature

import android.os.Bundle
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout
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
        activity?.actionBar?.title = "Home"

        setupTab()
    }

    private fun setupTab() {
        val adapter = TabAdapter(childFragmentManager)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                pager.currentItem = tab?.position ?: 0
            }

        })
    }

}