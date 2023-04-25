package com.example.personalfinanceapp.presentation.fragments.kyc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.domain.model.UserModel
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import com.example.personalfinanceapp.domain.usecases.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class KycViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _userModelLiveData = MutableLiveData<UserModel>()
    val userModelLiveData: LiveData<UserModel> get() = _userModelLiveData

    private var _isLogOuted = MutableLiveData<Boolean>()
    val isLogOuted: LiveData<Boolean> get() = _isLogOuted

    init {
        fetchUser()
    }

    fun logOut() {
        requestFlow {
            accuracyUseCase.logout().collect {
                _isLogOuted.postValue(it)
            }
        }
    }

    fun fetchUser() {
        requestFlow {
            userUseCase.getUserIdCurrent().collect {
                userUseCase.fetchUserInformation(it).collect { userModel ->
                    _userModelLiveData.postValue(userModel)
                }
            }
        }
    }

    fun updateUser(name: String, mobile: String, dob: String, address: String) {
        if (userModelLiveData.value != null) {
            val userId = userModelLiveData.value!!.id
            requestFlow {
                userUseCase.updateUser(userId!!, dob, address, name, mobile).collect {}
            }
        }
    }
}