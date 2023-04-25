package com.example.personalfinanceapp.presentation.fragments.payment

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
import com.example.personalfinanceapp.domain.usecases.accuracy.AccuracyUseCase
import com.example.personalfinanceapp.domain.usecases.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _userModelLiveData = MutableLiveData<UserModel>()
    val userModelLiveData: LiveData<UserModel> get() = _userModelLiveData

    private var _categorySelected = MutableLiveData<Category>()
    val categorySelected: LiveData<Category> get() = _categorySelected

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

    fun addBill(context: Context, amount: String, notes: String) {
        val category = categorySelected.value
        if (userModelLiveData.value != null) {
            val money = userModelLiveData.value!!.money
            if (money != null && money >= amount.toLong()) {
                handleAddBill(context, amount, notes, category)
            } else {
                Toast.makeText(context, "You don't have enough money", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleAddBill(context: Context, amount: String, notes: String, category: Category?) {
        val idCurrentDate = Constants.getCurrentDateId()
        val idBill = Constants.getIdBillRanDom()
        val userId = userModelLiveData.value!!.id
        val dailyBills = userModelLiveData.value!!.dailyBills
        val money = userModelLiveData.value!!.money.toString()

        if (dailyBills != null) {
            val dailyBill = dailyBills.find { it.id == idCurrentDate }
            dailyBills.remove(dailyBill)

            val bills = dailyBill!!.bills
            bills!!.add(Bill(idBill, category!!.categoryName, amount.toLong(), notes))

            dailyBills.add(dailyBill)
            requestFlow {
                userUseCase.addBill(userId!!, dailyBills).collect {
                    handleMinusMoney(context, userId, money, amount)
                }
            }
        } else {
            val haha = listOf(
                DailyBill(
                    idCurrentDate, idCurrentDate,
                    arrayListOf(
                        Bill(idBill, category!!.categoryName, amount.toLong(), notes)
                    ),
                )
            )
            requestFlow {
                userUseCase.addBill(
                    userId!!, haha
                ).collect {
                    handleMinusMoney(context, userId, money, amount)
                }
            }
        }
    }

    private fun handleMinusMoney(context: Context, userId: String, moneyOrigin: String, moneyMinus: String) {
        requestFlow {
            userUseCase.minusMoney(userId, moneyOrigin.toLong(), moneyMinus.toLong()).collect {
                Toast.makeText(context, "Done!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setCategory(category: Category) {
        _categorySelected.postValue(category)
    }
}