package com.azuka.themovieapp.presentation.feature.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import com.azuka.themovieapp.BaseFragment
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import com.azuka.themovieapp.presentation.entity.Movie
import com.azuka.themovieapp.presentation.feature.HomeFragmentDirections
import com.azuka.themovieapp.presentation.feature.favorites.FavoriteListAdapter
import com.azuka.themovieapp.presentation.feature.favorites.FavoriteViewModel
import com.azuka.themovieapp.presentation.feature.favorites.FavoritesFragmentDirections
import com.azuka.themovieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val movieViewModel: MovieViewModel by viewModels()

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var isFavoriteScreen = false

    override val viewLayout: Int = R.layout.fragment_movie

    override fun onFragmentReady(savedInstanceState: Bundle?) {
        isFavoriteScreen = navController.currentDestination?.id == R.id.favoritesFragment
        setupUI()
    }

    private fun setupUI() {
        if (isFavoriteScreen) {
            favoriteViewModel.getMovieList().observe(this, { favoriteList ->
                populateFavoriteMovieList(favoriteList)
            })
        } else {
            movieViewModel.getMovieList().observe(this, { movieList ->
                populateMovieList(movieList)
            })
        }

    }

    private fun populateMovieList(movieList: List<Movie>?) {
        movieList?.let {
            val adapter = MovieAdapter(movieList) { movie ->
                navigateToDetail(movie)
            }
            hideLoading()
            rvMovie.adapter = adapter
        }
    }

    private fun populateFavoriteMovieList(favoriteList: PagedList<FavoriteGeneral>) {
        val adapter = FavoriteListAdapter { favoriteData ->
            navigateToDetail(favoriteData)
        }

        adapter.submitList(favoriteList)
        hideLoading()
        rvMovie.adapter = adapter
    }

    private fun hideLoading() {
        loadingMovie.visibility = View.GONE
    }

    private fun navigateToDetail(data: Any) {
        val action = if (isFavoriteScreen) {
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                favoriteData = data as FavoriteGeneral,
                dataType = Constants.Movie.TAG_MOVIE_TYPE
            )
        } else {
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                (data as Movie).id, Constants.Movie.TAG_MOVIE_TYPE
            )
        }
        navController.navigate(action)
    }
}