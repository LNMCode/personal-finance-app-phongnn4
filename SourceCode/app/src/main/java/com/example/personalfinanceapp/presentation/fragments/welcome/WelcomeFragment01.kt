package com.example.personalfinanceapp.presentation.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.databinding.FragmentWelcome01Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment01 : Fragment() {

    private val welcomeViewModel: WelcomeViewModel by viewModels()

    private lateinit var binding: FragmentWelcome01Binding
    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorContainer: LinearLayoutCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWelcome01Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBoardingItems()
        setupIndicator()
        setUpEventButtons()
    }

    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemsAdapter(welcomeViewModel.listDataOnBoarding)
        val onBoardingViewPager = binding.vpOnBoarding
        onBoardingViewPager.adapter = onBoardingItemsAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        // Set onClick to the second Welcome Fragment
        binding.imgNext.setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount) {
                onBoardingViewPager.currentItem += 1
            } else {
                navigateToLogin()
            }
        }
    }

    private fun setupIndicator() {
        val applicationContext = requireActivity().applicationContext
        indicatorContainer = binding.indicatorsContainer
        // Create an empty array to store indicator images
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)

        val layParams: LinearLayoutCompat.LayoutParams =
            LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).also {
                it.setMargins(8, 0, 8, 0)
            }
        for (i in indicators.indices) {
            indicators[i] = AppCompatImageView(applicationContext).also {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicators(position: Int) {
        val applicationContext = requireActivity().applicationContext

        // The number of children in the group
        val childCount = indicatorContainer.childCount

        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    private fun setUpEventButtons() {
        binding.btnSignup.setOnClickListener {
            navigateToSignUp()
        }

        binding.btnLogin.setOnClickListener {
            navigateToLogin()
        }

        binding.txtSkip.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_welcomeFragment012_to_loginFragment2)
    }

    private fun navigateToSignUp() {
        findNavController().navigate(R.id.action_welcomeFragment012_to_signUpFragment2)
    }

}