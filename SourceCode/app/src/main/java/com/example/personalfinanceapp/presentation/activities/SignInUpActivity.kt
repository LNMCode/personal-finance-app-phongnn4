package com.example.personalfinanceapp.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.personalfinanceapp.databinding.ActivityMainBinding
import com.example.personalfinanceapp.databinding.ActivitySignInUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInUpBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}