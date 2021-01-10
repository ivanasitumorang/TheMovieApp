package com.azuka.themovieapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.feature.movie.MovieFragment
import com.azuka.themovieapp.presentation.feature.tvseries.TvSeriesFragment


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class TabAdapter(context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    private val titles = arrayOf(
        context.getString(R.string.tab_movies),
        context.getString(R.string.tab_tv_series)
    )

    override fun getItem(position: Int): Fragment {
        return if (position == 1) {
            TvSeriesFragment()
        } else {
            MovieFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}