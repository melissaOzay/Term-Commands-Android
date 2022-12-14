package com.example.termcommandsandroid.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.ui.command.CommandsFragment
import com.example.termcommandsandroid.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    var fragmentManager: FragmentManager? = null
    private val homeFragment by lazy {
        HomeFragment()
    }

    private val commandsFragment by lazy {
        CommandsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        openFragment(homeFragment)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> openFragment(homeFragment)

                R.id.shop -> openFragment(commandsFragment)
                else -> {
                    openFragment(homeFragment)

                }

            }
            true
        }
    }


    fun openFragment(fragment: Fragment) {
        fragmentManager = getSupportFragmentManager()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainerView3, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}


