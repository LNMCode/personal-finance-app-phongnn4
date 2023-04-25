package com.example.personalfinanceapp.presentation.fragments.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleTime()
    }

    private fun handleTime() {
        CoroutineScope(Dispatchers.Main).launch {

            val time = measureTimeMillis {
                iconAppearing()
                textAppearing()
                loadingAppearing()
            }
            Log.i("MY_TIME", "Time = $time ms")
        }

        Handler(Looper.myLooper()!!).postDelayed(
            {
                findNavController().navigate(R.id.action_splashFragment2_to_welcomeFragment012)
                binding.cvProgressLine.hasCompletedDownload()
            }, 7000
        )
    }

    private suspend fun loadingAppearing() {
        binding.cvProgressLine.visibility = View.VISIBLE
        // start the animation of progress bar
        AnimationUtils.loadAnimation(context, R.anim.slide_in_right).also { anim ->
            binding.cvProgressLine.startAnimation(anim)
        }
        binding.cvProgressLine.animation()
        delay(500)
    }

    private suspend fun textAppearing() {
        binding.tvAppTitle.visibility = View.VISIBLE
        // textView appears
        AnimationUtils.loadAnimation(context, R.anim.tv_anim).also { anim ->
            binding.tvAppTitle.startAnimation(anim)
        }
        delay(1500)
    }

    private suspend fun iconAppearing() {
        binding.imgAppIcon.visibility = View.VISIBLE
        // Start animation of the image icon
        AnimationUtils.loadAnimation(context, R.anim.image_anim).also { anim ->
            binding.imgAppIcon.startAnimation(anim)
        }
        delay(1500)
    }

}