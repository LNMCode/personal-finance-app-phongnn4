package com.example.personalfinanceapp.presentation.fragments.welcome

import androidx.lifecycle.ViewModel
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.domain.model.OnBoardingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(): ViewModel() {

    val listDataOnBoarding = listOf(
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

}