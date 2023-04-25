package com.example.personalfinanceapp.presentation.fragments.payment

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

    fun addBill(amount: String, notes: String) {
        val category = categorySelected.value
        if (userModelLiveData.value != null) {
            val idCurrentDate = Constants.getCurrentDateId()
            val idBill = Constants.getIdBillRanDom()
            val userId = userModelLiveData.value!!.id
            val dailyBills = userModelLiveData.value!!.dailyBills

            if (dailyBills != null) {
                val dailyBill = dailyBills.find { it.id == idCurrentDate }
                dailyBills.remove(dailyBill)

                val bills = dailyBill!!.bills
                bills!!.add(Bill(idBill, category!!.categoryName, amount.toLong(), notes))
                dailyBill.bills!!.clear()
                dailyBill.bills!!.addAll(bills)
                dailyBills.add(dailyBill)
                requestFlow {
                    userUseCase.addBill(userId!!, dailyBills)
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
                    )
                }
            }
        }
    }

    fun setCategory(category: Category) {
        _categorySelected.postValue(category)
    }
}