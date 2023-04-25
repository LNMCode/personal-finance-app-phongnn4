package com.example.personalfinanceapp.presentation.fragments.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.domain.model.UserModel
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import com.example.personalfinanceapp.domain.usecases.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _userModelLiveData = MutableLiveData<UserModel>()
    val userModelLiveData: LiveData<UserModel> get() = _userModelLiveData

    fun fetchUser() {
        requestFlow {
            userUseCase.getUserIdCurrent().collect {
                userUseCase.fetchUserInformation(it).collect { userModel ->
                    _userModelLiveData.postValue(userModel)
                }
            }
        }
    }

}