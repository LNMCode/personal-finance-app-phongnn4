package com.example.personalfinanceapp.presentation.fragments.billsList

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
class BillsListViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _userModelLiveData = MutableLiveData<UserModel>()
    val userModelLiveData: LiveData<UserModel> get() = _userModelLiveData

    private var _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> get() = _isDone

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

    fun setCostBill(money: String, bill: Bill) {
        val idCurrentDate = Constants.getCurrentDateId()
        val userId = userModelLiveData.value!!.id
        val dailyBills = userModelLiveData.value!!.dailyBills
        if (dailyBills != null) {
            val dailyBill = dailyBills.find { it.id == idCurrentDate }
            if (dailyBill != null) {
                dailyBills.remove(dailyBill)

                val bills = dailyBill.bills
                val billFind = bills?.find { it.id == bill.id }
                if (billFind != null) {
                    bills.remove(billFind)

                    bills.add(Bill(bill.id, bill.category, money.toLong(), bill.note))

                    dailyBills.add(dailyBill)
                    requestFlow {
                        userUseCase.updateBill(userId!!, dailyBills).collect {
                            _isDone.postValue(it)
                        }
                    }
                }
            }
        }
    }
}