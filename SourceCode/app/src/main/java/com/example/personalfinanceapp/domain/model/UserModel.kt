package com.example.personalfinanceapp.domain.model

import com.example.personalfinanceapp.domain.model.bill.DailyBill

data class UserModel(
    var id: String? = null,
    var DOB: String? = null,
    var address: String? = null,
    var mobile: String? = null,
    var money: Long? = null,
    var name: String? = null,
    var dailyBills: List<DailyBill>? = null,
    var creditLimit: CreditLimit? = null,
) {

}