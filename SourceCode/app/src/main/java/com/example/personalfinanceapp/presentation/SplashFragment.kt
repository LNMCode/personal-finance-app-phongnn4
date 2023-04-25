package com.example.personalfinanceapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentSplashBinding
import com.example.personalfinanceapp.ui.ProgressLineView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var progressLineView: ProgressLineView

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        progressLineView = binding.cvProgressLine

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
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment01)
                progressLineView.hasCompletedDownload()
            }, 7000
        )

        return binding.root
    }

    private suspend fun loadingAppearing() {
        binding.cvProgressLine.visibility = View.VISIBLE
        // start the animation of progress bar
        AnimationUtils.loadAnimation(context, R.anim.slide_in_right).also { anim ->
            binding.cvProgressLine.startAnimation(anim)
        }
        progressLineView.animation()
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