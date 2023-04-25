package com.example.personalfinanceapp.domain.model.bill

import java.time.LocalDate

data class DailyBill(
    var id: String? = null,
    var date: LocalDate? = null,
    var bills: ArrayList<Bill>,
    var money: Long? = null,
    private var _amount: Long = 0
) {

    var amount: Long
        get() = _amount
        set(value) {
            _amount = value
        }

    init {
        amount = bills.sumOf { it.cost }
    }
}
