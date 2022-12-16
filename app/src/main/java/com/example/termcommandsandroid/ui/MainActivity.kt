package com.example.termcommandsandroid.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.databinding.ActivityMainBinding
import com.example.termcommandsandroid.databinding.FragmentHomeBinding
import com.example.termcommandsandroid.ui.command.CommandsFragment
import com.example.termcommandsandroid.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragmentContainerView3)
     NavigationUI.setupWithNavController(navHostFragment,navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.categoriesFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {

                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

    }
}


