package com.example.personalfinanceapp.domain.repo.bill

import kotlinx.coroutines.flow.Flow

interface BillRepository {
    suspend fun createBill(amount: Int, category: String, note: String) : Flow<Boolean>


}