package com.azuka.themovieapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.azuka.themovieapp.feature.movie.MovieFragment
import com.azuka.themovieapp.feature.tvseries.TvSeriesFragment


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class TabAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    companion object {
        private val TITLES = arrayOf(
            "Movie",
            "Tv Series"
        )
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 1) {
            TvSeriesFragment()
        } else {
            MovieFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TITLES[position]
    }
}