// Attached: MainActivity.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: MainActivity class
// =============================================================================
// Description:
// This is the main class, and it holds two fragments, the map and history.
// This class is responsible for switching between those fragments.
// =============================================================================
package com.example.excessgone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.excessgone.Presentation.AboutFragment
import com.example.excessgone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Using binding instead of using FindViewById as binding's performance is better
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // defining fragment manager
        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val historyFragment: Fragment = HistoryFragment()
        val mapFragment: Fragment = MapFragment()
        val aboutFragment : Fragment = AboutFragment()

        // handle navigation selection
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                // each button in the navigation bar is assigned to each fragment
                R.id.nav_history -> fragment = historyFragment
                R.id.nav_maps -> fragment = mapFragment
                R.id.nav_about -> fragment = aboutFragment
            }
            // call function for changing buttons on navigation bar
            replaceFragment(fragment)
            true
        }

        // Set default selection where the user will first look at About Us page
        binding.bottomNavigation.selectedItemId = R.id.nav_about

    }

    // this function replaces the current fragment with the one the user requests
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.rlContainer, fragment)

        // this is responsible for loading all fragment UIs
        fragmentTransaction.commit()
    }

}