package com.example.personalfinanceapp.presentation.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.personalfinanceapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("###3", "Back press")
        handleShowBackPress()
    }

    private fun handleShowBackPress() {
        if (!binding.bottomNavBar.isVisible) {
            binding.bottomNavBar.visibility = View.VISIBLE
        }
    }
}