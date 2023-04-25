package com.example.personalfinanceapp.domain.model

import com.example.personalfinanceapp.domain.model.bill.DailyBill

data class UserModel(
    val id: String,
    val DOB: String,
    val address: String,
    val mobile: String,
    val money: String,
    val name: String,
    val dailyBills: DailyBill,
    val creditLimit: CreditLimit,
) {
}