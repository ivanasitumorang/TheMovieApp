package com.azuka.themovieapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.azuka.themovieapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavController()
        setupBottomNavigationView()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.navHostFragment
        ) as NavHostFragment

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)
    }

    private fun setupBottomNavigationView() {
        val homeColorStateList =
            ContextCompat.getColorStateList(this, R.color.bottom_nav_color_home)

        val favoriteColorStateList =
            ContextCompat.getColorStateList(this, R.color.bottom_nav_color_favorite)

        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {

                    bottomNavView.apply {
                        itemTextColor = homeColorStateList
                        itemIconTintList = homeColorStateList
                        visibility = View.VISIBLE
                    }
                }
                R.id.favoritesFragment -> {

                    bottomNavView.apply {
                        itemTextColor = favoriteColorStateList
                        itemIconTintList = favoriteColorStateList
                        visibility = View.VISIBLE
                    }
                }
                else -> bottomNavView.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}