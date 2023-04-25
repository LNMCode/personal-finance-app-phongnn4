package com.example.personalfinanceapp.presentation.fragments.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalfinanceapp.R
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.domain.model.OnBoardingItem
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import com.example.personalfinanceapp.presentation.fragments.signUp.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
) : BaseViewModel() {

    private var _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> get() = _isLogged

    fun checkLogged() {
        requestFlow {
            accuracyUseCase.isLogged().collect {
                _isLogged.postValue(it)
            }
        }
    }

}