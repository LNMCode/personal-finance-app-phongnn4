package com.example.personalfinanceapp.presentation.fragments.dashboard

import com.example.personalfinanceapp.domain.model.bill.Bill
import java.time.LocalDate

data class RecentBillsItemModel(
    var bill: Bill,
    var date: String,
)