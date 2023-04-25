package com.example.personalfinanceapp.domain.model.bill

import java.time.LocalDate

data class DailyBill(
    var id: String? = null,
    var date: String? = null,
    var bills: ArrayList<Bill>? = null,
)
