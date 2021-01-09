package com.azuka.themovieapp.presentation.feature.favorites

import android.os.Bundle
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by ivanaazuka on 08/01/21.
 * Android Engineer
 */

@AndroidEntryPoint
class FavoritesFragment : BaseFragment() {
    override val viewLayout: Int = R.layout.fragment_favorites

    override fun onFragmentReady(savedInstanceState: Bundle?) {

    }
}