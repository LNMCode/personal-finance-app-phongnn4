package com.example.personalfinanceapp.presentation.fragments.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
) : BaseViewModel() {

    private var _signUpStateMutableLiveData = MutableLiveData<SignUpState>()
    val signUpStateMutableLiveData: LiveData<SignUpState> get() = _signUpStateMutableLiveData

    fun signUp(email: String?, password: String?) {
        val isCorrect = checkEmailAndPassword(email, password)
        if (!isCorrect) return
        requestFlow {
            accuracyUseCase.signUpWithEmailAndPassword(email!!, password!!).collect {
                _signUpStateMutableLiveData.postValue(it)
            }
        }
    }

    private fun checkEmailAndPassword(email: String?, password: String?): Boolean {
        val isEmptyEmail = checkNullOrBlankValue(email)
        if (isEmptyEmail) {
            _signUpStateMutableLiveData.postValue(
                SignUpState.Result(
                    false,
                    "Please enter email"
                )
            )
            return false
        }
        val isEmptyPassword = checkNullOrBlankValue(password)
        if (isEmptyPassword) {
            _signUpStateMutableLiveData.postValue(
                SignUpState.Result(
                    false,
                    "Please enter password"
                )
            )
            return false
        }
        return true
    }

    private fun checkNullOrBlankValue(value: String?): Boolean {
        return value.isNullOrBlank()
    }

}