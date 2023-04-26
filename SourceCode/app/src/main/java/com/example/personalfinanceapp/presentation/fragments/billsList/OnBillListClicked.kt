package com.example.personalfinanceapp.presentation.fragments.billsList

import com.example.personalfinanceapp.domain.model.bill.Bill

interface OnBillListClicked {
    fun onClicked(bill: Bill)
}