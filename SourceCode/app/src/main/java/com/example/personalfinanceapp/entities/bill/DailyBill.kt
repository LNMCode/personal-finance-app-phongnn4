package com.example.personalfinanceapp.entities.bill

import java.time.LocalDate

data class DailyBill(
    val id: Int,
    val date: LocalDate,
    private var _billList: ArrayList<Bill>,
    private var _amount: Long = 0
) {
    val billList: ArrayList<Bill>
        get() = _billList

    var amount: Long
        get() = _amount
        set(value) {
            _amount = value
        }

    init {
        amount = billList.sumOf { it.cost }
    }
}
