package com.example.personalfinanceapp.presentation.fragments.kyc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class KycViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
) : BaseViewModel() {

    private var _isLogOuted = MutableLiveData<Boolean>()
    val isLogOuted: LiveData<Boolean> get() = _isLogOuted

    fun logOut() {
        requestFlow {
            accuracyUseCase.logout().collect {
                _isLogOuted.postValue(it)
            }
        }
    }
}