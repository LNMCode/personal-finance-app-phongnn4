package com.example.personalfinanceapp.presentation.fragments.income

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
class IncomeViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _incomeLiveData = MutableLiveData<Boolean>()
    val incomeLiveData: LiveData<Boolean> get() = _incomeLiveData

    fun addIncome(income: String) {
        requestFlow {
            userUseCase.getUserIdCurrent().collect {
                userUseCase.fetchUserInformation(it).collect { userModel ->
                    val total = income.toLong() + userModel?.money!!
                    userUseCase.addIncome(it, total).collect { isDone ->
                        _incomeLiveData.postValue(isDone)
                    }
                }
            }
        }
    }
}