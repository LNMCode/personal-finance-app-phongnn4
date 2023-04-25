package com.example.personalfinanceapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.presentation.adapter.OnBoardingItemsAdapter
import com.example.personalfinanceapp.databinding.FragmentWelcome01Binding
import com.example.personalfinanceapp.entities.OnBoardingItem

class WelcomeFragment01 : Fragment() {

    private lateinit var binding: FragmentWelcome01Binding
    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorContainer: LinearLayoutCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome01, container, false)

        setOnBoardingItems()
        setupIndicator()

        return binding.root
    }

    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    R.drawable.onboarding01,
                    "Manage Your Money",
                    "Saving money is one of the essential aspects of building wealth and having a secure financial future"
                ),
                OnBoardingItem(
                    R.drawable.onboarding02,
                    "It gives you a better future",
                    "You can secure your future, indulge in the best of things that life has to offer and live a very fulfilling life"
                ),
                OnBoardingItem(
                    R.drawable.onboarding03,
                    "It provides for your children’s education",
                    "With a considerable amount of savings, you can fuel your children’s dreams and pay for the best schools and colleges across the world"
                )
            )
        )
        val onBoardingViewPager = binding.vpOnBoarding as ViewPager2
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

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_welcomeFragment01_to_loginFragment)
    }

    private fun navigateToSignUp() {
        findNavController().navigate(R.id.action_welcomeFragment01_to_signUpFragment)
    }

}