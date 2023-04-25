package com.example.personalfinanceapp.presentation.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
) : BaseViewModel() {

    private var _signInStateMutableLiveData = MutableLiveData<LoginState>()
    val signInStateMutableLiveData: LiveData<LoginState> get() = _signInStateMutableLiveData

    fun signIn(email: String?, password: String?) {
        val isCorrect = checkEmailAndPassword(email, password)
        if (!isCorrect) return
        requestFlow {
            accuracyUseCase.signInWithEmailAndPassword(email!!, password!!).collect {
                _signInStateMutableLiveData.postValue(it)
            }
        }
    }

    private fun checkEmailAndPassword(email: String?, password: String?): Boolean {
        val isEmptyEmail = checkNullOrBlankValue(email)
        if (isEmptyEmail) {
            _signInStateMutableLiveData.postValue(
                LoginState.Result(
                    false,
                    "Please enter email"
                )
            )
            return false
        }
        val isEmptyPassword = checkNullOrBlankValue(password)
        if (isEmptyPassword) {
            _signInStateMutableLiveData.postValue(
                LoginState.Result(
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