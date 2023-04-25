package com.example.personalfinanceapp.presentation.fragments.buget

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.personalfinanceapp.base.BaseViewModel
import com.example.personalfinanceapp.constants.Constants
import com.example.personalfinanceapp.domain.model.UserModel
import com.example.personalfinanceapp.domain.model.bill.Bill
import com.example.personalfinanceapp.domain.model.bill.DailyBill
import com.example.personalfinanceapp.domain.model.category.Category
import com.example.personalfinanceapp.domain.usecases.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _userModelLiveData = MutableLiveData<UserModel>()
    val userModelLiveData: LiveData<UserModel> get() = _userModelLiveData

    private var _dateSelected = MutableLiveData<String>()
    val dateSelected: LiveData<String> get() = _dateSelected

    init {
        fetchUser()
    }

    private fun fetchUser() {
        requestFlow {
            userUseCase.getUserIdCurrent().collect {
                userUseCase.fetchUserInformation(it).collect { userModel ->
                    _userModelLiveData.postValue(userModel)
                }
            }
        }
    }

    fun updateCreditLimit(context: Context, amount: String, notes: String) {
        if (userModelLiveData.value != null) {
            val money = userModelLiveData.value!!.money
            val userId = userModelLiveData.value!!.id
            val type = _dateSelected.value!!
            if (money != null && money >= amount.toLong()) {
                val startFrom = Constants.getCurrentDateId()
                requestFlow {
                    userUseCase.updateCreditLimit(userId!!, amount.toLong(), notes, type, startFrom).collect {
                        Toast.makeText(context, "Done!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "You don't have enough money", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setDateType(date: String) {
        _dateSelected.postValue(date)
    }
}